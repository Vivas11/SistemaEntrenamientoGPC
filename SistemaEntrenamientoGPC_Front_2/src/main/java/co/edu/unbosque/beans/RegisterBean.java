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
    private String nivelCompetitiva = ""; 
    private int semestre = 1;
    private String cargo = "";
    private boolean esEntrenador = false;

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

    public void showStickyLogin(String code, String content) {
        FacesContext context = FacesContext.getCurrentInstance();
        switch (code) {
            case "201":
                context.addMessage("sticky-key",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "Se ha creado su usuario"));
                break;
            case "406":
                context.addMessage("sticky-key",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
                break;
            default:
                System.out.println("Error crítico al crear cuenta");
                System.out.println("Status code: " + code);
                System.out.println("reason: " + content);
                context.addMessage("sticky-key",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Crítico",
                                "Error al crear, comuniquese con el administrador"));
        }
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNivelCompetitiva() { return nivelCompetitiva; }
    public void setNivelCompetitiva(String nivelCompetitiva) { this.nivelCompetitiva = nivelCompetitiva; }

    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public boolean isEsEntrenador() { return esEntrenador; }
    public void setEsEntrenador(boolean esEntrenador) { this.esEntrenador = esEntrenador; }
}
