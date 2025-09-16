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
	
	EstudianteService estService;
	ProfesorService proService;
	AdministradorService adminService;
	
    private String fullName;
    private String email;
    private Integer age;
    private String password;
    private String confirmPassword;
    private String type;

	public void crearUsuario() {
		
		
		
		fullName = AESUtil.encrypt(fullName);
		password = AESUtil.encrypt(password);
		String json = "{";
		json += "\"nombre\" : \"" + fullName + "\",";
		json += "\"correo\" : \"" + email + "\"";
		json += "\"edad\" : \"" + age + "\"";
		json += "\"contrasena\" : \"" + password + "\"";
		json += "}";

		String respuesta = "";
		switch (type) {
		case "Estudiante": {

			 respuesta = estService.doPost(json);
			break;
		}
		case "Administrador": {

			 respuesta = adminService.doPost(json);
			break;
		}
		case "Profesor": {

			 respuesta = proService.doPost(json);
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
}
