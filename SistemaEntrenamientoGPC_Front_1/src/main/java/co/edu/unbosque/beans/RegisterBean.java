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
public class RegisterBean {
	private static final long serialVersionUID = 1L;

	private String fullName;
	private String email;
	private Integer age;
	private String password;
	private String confirmPassword;
	private String type;
	private String nivelCompetitiva;
	private int semestre;
	private String cargo;
	private boolean esEntrenador;

	public void crearUsuario() {
		System.out.println(nivelCompetitiva + semestre + cargo + esEntrenador);
		
		
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
			System.out.println(json);
			
			respuesta = EstudianteService.doPostJson(json);
			break;
		}
		case "Administrador": {

			json += "}";
			System.out.println(json);
			respuesta = AdministradorService.doPostJson(json);
			break;
		}
		case "Profesor": {
			json += ",\"cargo\":\"" + cargo + "\",";
			json += "\"esEntrenador\":\"" + esEntrenador + "\"";
			json += "}";
			System.out.println(json);
			respuesta = ProfesorService.doPostJson(json);
			break;
		}
		default:
			break;
		}

		System.out.println("Respuesta del servidor: " + respuesta);

//		String[] data = respuesta.split("\n");
//		showStickyLogin(data[0], data[1]);
		fullName = "";
		email = "";
		age = 0;
		password = "";
	}

	public void showSticky() {
		FacesContext.getCurrentInstance().addMessage("sticky-key",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", "Message Content"));
	}

	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", content));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else {
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error al crear," + "comuniquese con el administrador"));
		}
	}

	// Getters y Setters
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNivelCompetitiva() {
		return nivelCompetitiva;
	}

	public void setNivelCompetitiva(String nivelCompetitiva) {
		this.nivelCompetitiva = nivelCompetitiva;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isEsEntrenador() {
		return esEntrenador;
	}

	public void setEsEntrenador(boolean esEntrenador) {
		this.esEntrenador = esEntrenador;
	}
	
}
