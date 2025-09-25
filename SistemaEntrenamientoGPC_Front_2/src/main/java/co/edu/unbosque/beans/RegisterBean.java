package co.edu.unbosque.beans;

import co.edu.unbosque.service.AdministradorService;
import co.edu.unbosque.service.EstudianteService;
import co.edu.unbosque.service.ProfesorService;
import co.edu.unbosque.util.AESUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 * Bean administrado para el registro de usuarios. Gestiona la creación de
 * diferentes tipos de usuarios, incluyendo Estudiante, Profesor y
 * Administrador. Se encarga de validar los campos de entrada, encriptar datos
 * sensibles y enviar la información al servicio correspondiente para el
 * registro.
 */
@Named(value = "registerbean")
@RequestScoped
public class RegisterBean {

	private static final long serialVersionUID = 1L;

	/** Nombre completo del usuario */
	private String fullName;

	/** Correo electrónico del usuario */
	private String email;

	/** Edad del usuario */
	private Integer age;

	/** Contraseña proporcionada por el usuario (antes de encriptar) */
	private String password;

	/** Confirmación de la contraseña para validar la entrada del usuario */
	private String confirmPassword;

	/** Tipo de usuario a registrar: Estudiante, Profesor o Administrador */
	private String type;

	/** Nivel competitivo del estudiante (si es estudiante) */
	private String nivelCompetitiva = "";

	/** Semestre del estudiante (por defecto 1) */
	private int semestre = 1;

	/** Cargo o función del profesor (si es profesor) */
	private String cargo = "";

	/** Indica si el profesor es entrenador */
	private boolean esEntrenador = false;

	/**
	 * Método para crear un nuevo usuario según el tipo seleccionado. Valida datos
	 * obligatorios según el tipo, encripta el nombre y la contraseña, construye un
	 * JSON con la información y llama al servicio correspondiente para registrar el
	 * usuario. Muestra mensajes informativos o de error según la respuesta
	 * recibida.
	 */
	public void crearUsuario() {
		if ("Estudiante".equals(type)) {
			if (nivelCompetitiva == null || nivelCompetitiva.isEmpty()) {
				showStickyLogin("406", "Seleccionen todos los datos del estudiante.");
				return;
			}
		}

		if ("Profesor".equals(type)) {
			if (cargo == null || cargo.isEmpty()) {
				showStickyLogin("406", "Ingrese el cargo del profesor.");
				return;
			}
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
		case "Estudiante":
			json += ",\"nivelCompe\":\"" + nivelCompetitiva + "\",";
			json += "\"semestre\":\"" + semestre + "\"";
			json += "}";
			respuesta = EstudianteService.doPostJson(json);
			break;

		case "Administrador":
			json += "}";
			respuesta = AdministradorService.doPostJson(json);
			break;

		case "Profesor":
			json += ",\"cargo\":\"" + cargo + "\",";
			json += "\"esEntrenador\":\"" + esEntrenador + "\"";
			json += "}";
			respuesta = ProfesorService.doPostJson(json);
			break;

		default:
			showStickyLogin("406", "Tipo de usuario no válido.");
			return;
		}

		System.out.println("Respuesta del servidor: " + respuesta);

		String[] data = respuesta.split("\n");
		showStickyLogin(data[0], data[1]);

		limpiarCampos();
	}

	/**
	 * Limpia todos los campos del formulario dejándolos en estado inicial.
	 */
	private void limpiarCampos() {
		fullName = "";
		email = "";
		age = null;
		password = "";
		confirmPassword = "";
		nivelCompetitiva = "";
		semestre = 1;
		cargo = "";
		esEntrenador = false;
		type = "";
	}

	/**
	 * Muestra un mensaje persistente en la interfaz de usuario basado en el código
	 * y contenido recibidos.
	 * 
	 * @param code    Código de respuesta para determinar el tipo de mensaje.
	 * @param content Mensaje de texto a mostrar.
	 */
	public void showStickyLogin(String code, String content) {
		FacesContext context = FacesContext.getCurrentInstance();
		switch (code) {
		case "201":
			context.addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "Se ha creado su usuario"));
			break;
		case "406":
			context.addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
			break;
		default:
			System.out.println("Error crítico al crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			context.addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Crítico",
					"Error al crear, comuniquese con el administrador"));
		}
	}
	/**
	 * Obtiene el nombre completo del usuario.
	 * @return Nombre completo
	 */
	public String getFullName() {
	    return fullName;
	}

	/**
	 * Establece el nombre completo del usuario.
	 * @param fullName Nombre completo a establecer
	 */
	public void setFullName(String fullName) {
	    this.fullName = fullName;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 * @return Correo electrónico
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 * @param email Correo electrónico a establecer
	 */
	public void setEmail(String email) {
	    this.email = email;
	}

	/**
	 * Obtiene la edad del usuario.
	 * @return Edad
	 */
	public Integer getAge() {
	    return age;
	}

	/**
	 * Establece la edad del usuario.
	 * @param age Edad a establecer
	 */
	public void setAge(Integer age) {
	    this.age = age;
	}

	/**
	 * Obtiene la contraseña del usuario (en texto plano, antes de encriptar).
	 * @return Contraseña
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 * @param password Contraseña a establecer
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * Obtiene la confirmación de la contraseña.
	 * @return Confirmación de contraseña
	 */
	public String getConfirmPassword() {
	    return confirmPassword;
	}

	/**
	 * Establece la confirmación de la contraseña.
	 * @param confirmPassword Confirmación de contraseña a establecer
	 */
	public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	}

	/**
	 * Obtiene el tipo de usuario (Estudiante, Profesor, Administrador).
	 * @return Tipo de usuario
	 */
	public String getType() {
	    return type;
	}

	/**
	 * Establece el tipo de usuario.
	 * @param type Tipo de usuario a establecer
	 */
	public void setType(String type) {
	    this.type = type;
	}

	/**
	 * Obtiene el nivel competitivo del estudiante.
	 * @return Nivel competitivo
	 */
	public String getNivelCompetitiva() {
	    return nivelCompetitiva;
	}

	/**
	 * Establece el nivel competitivo del estudiante.
	 * @param nivelCompetitiva Nivel competitivo a establecer
	 */
	public void setNivelCompetitiva(String nivelCompetitiva) {
	    this.nivelCompetitiva = nivelCompetitiva;
	}

	/**
	 * Obtiene el semestre del estudiante.
	 * @return Semestre
	 */
	public int getSemestre() {
	    return semestre;
	}

	/**
	 * Establece el semestre del estudiante.
	 * @param semestre Semestre a establecer
	 */
	public void setSemestre(int semestre) {
	    this.semestre = semestre;
	}

	/**
	 * Obtiene el cargo del profesor.
	 * @return Cargo
	 */
	public String getCargo() {
	    return cargo;
	}

	/**
	 * Establece el cargo del profesor.
	 * @param cargo Cargo a establecer
	 */
	public void setCargo(String cargo) {
	    this.cargo = cargo;
	}

	/**
	 * Indica si el profesor es entrenador.
	 * @return true si es entrenador, false en caso contrario
	 */
	public boolean isEsEntrenador() {
	    return esEntrenador;
	}

	/**
	 * Establece si el profesor es entrenador.
	 * @param esEntrenador true para indicar que es entrenador, false en caso contrario
	 */
	public void setEsEntrenador(boolean esEntrenador) {
	    this.esEntrenador = esEntrenador;
	}


}
