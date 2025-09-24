package co.edu.unbosque.beans;

import co.edu.unbosque.service.AdministradorService;
import co.edu.unbosque.service.EstudianteService;
import co.edu.unbosque.service.ProfesorService;
import co.edu.unbosque.util.AESUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named(value = "registerbean")
@RequestScoped
/**
 * Bean de request encargado del registro de nuevos usuarios de distintos tipos
 * (Administrador, Estudiante y Profesor). Construye manualmente el JSON de
 * salida según el perfil seleccionado y cifra datos sensibles (nombre y
 * contraseña) antes de enviarlos.
 */
public class RegisterBean {
	private static final long serialVersionUID = 1L;

	/** Nombre completo del usuario. */
	private String fullName;
	/** Correo electrónico. */
	private String email;
	/** Edad del usuario. */
	private Integer age;
	/** Contraseña (texto plano antes del cifrado). */
	private String password;
	/** Confirmación de la contraseña. */
	private String confirmPassword;
	/** Tipo de usuario a crear (Administrador/Estudiante/Profesor). */
	private String type;
	/** Nivel de competitividad (sólo Estudiante). */
	private String nivelCompetitiva;
	/** Semestre académico (sólo Estudiante). */
	private int semestre;
	/** Cargo laboral (sólo Profesor). */
	private String cargo;
	/** Indica si el profesor actúa como entrenador. */
	private boolean esEntrenador;

	/**
	 * Orquesta el proceso de creación de un usuario según el tipo seleccionado. Se
	 * cifran nombre y contraseña y se construye un JSON específico por rol.
	 */
	public void crearUsuario() {
		if (nivelCompetitiva != null && nivelCompetitiva.equals("")) {
			showStickyLogin("406", "Seleccionen todos los datos.");
			return;
		}

		fullName = AESUtil.encrypt(fullName);
		password = AESUtil.encrypt(password);
		String json = "{";
		json += "\"nombre\":\"" + fullName + "\",";
		json += "\"correo\":\"" + email + "\",";
		json += "\"edad\":" + age + ",";
		json += "\"contrasena\":\"" + password + "\"";

		String respuesta = "";
		switch (type) {
		case "Estudiante": {
			json += ",\"nivelCompe\":\"" + nivelCompetitiva + "\",";
			json += "\"semestre\":\"" + semestre + "\"";
			json += "}";
			respuesta = EstudianteService.doPostJson(json);
			break;
		}
		case "Administrador": {
			json += "}";
			respuesta = AdministradorService.doPostJson(json);
			break;
		}
		case "Profesor": {
			json += ",\"cargo\":\"" + cargo + "\",";
			json += "\"esEntrenador\":\"" + esEntrenador + "\"";
			json += "}";
			respuesta = ProfesorService.doPostJson(json);
			break;
		}
		default:
			break;
		}

		String[] data = respuesta.split("\n");
		showStickyLogin(data[0], data[1]);
		fullName = "";
		email = "";
		age = 0;
		password = "";
		nivelCompetitiva = "";
		semestre = 1;
		cargo = "";
		esEntrenador = false;
	}

	/**
	 * Muestra un mensaje genérico de prueba (no utilizado en flujo principal).
	 */
	public void showSticky() {
		FacesContext.getCurrentInstance().addMessage("sticky-key",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", "Message Content"));
	}

	/**
	 * Mapea códigos de respuesta del servicio a mensajes UI.
	 * 
	 * @param code    Código HTTP simulado (201, 406, etc.).
	 * @param content Mensaje adicional.
	 */
	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado su usuario"));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error al crear,comuniquese con el administrador"));
		}
	}

	// Getters y Setters
	/**
	 * @return Nombre completo del usuario.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName Nombre completo a establecer.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return Correo electrónico.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email Correo electrónico.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Edad del usuario.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age Edad del usuario.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return Contraseña en texto plano (antes de cifrar).
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Contraseña en texto plano.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Confirmación de la contraseña.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword Confirmación de contraseña.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return Tipo de usuario a crear.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Tipo de usuario (Administrador/Profesor/Estudiante).
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Nivel de competitividad (Estudiante).
	 */
	public String getNivelCompetitiva() {
		return nivelCompetitiva;
	}

	/**
	 * @param nivelCompetitiva Nivel competitivo.
	 */
	public void setNivelCompetitiva(String nivelCompetitiva) {
		this.nivelCompetitiva = nivelCompetitiva;
	}

	/**
	 * @return Semestre académico.
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * @param semestre Semestre académico.
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	/**
	 * @return Cargo del profesor.
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo Cargo del profesor.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return Indica si el profesor es entrenador.
	 */
	public boolean isEsEntrenador() {
		return esEntrenador;
	}

	/**
	 * @param esEntrenador Marca si el profesor es entrenador.
	 */
	public void setEsEntrenador(boolean esEntrenador) {
		this.esEntrenador = esEntrenador;
	}
	
}
