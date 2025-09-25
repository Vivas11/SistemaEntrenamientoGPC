package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.Problema;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.ProblemaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * Bean de vista para la gestión de problemas (ejercicios) de programación.
 * Permite listar, crear y eliminar problemas consumiendo servicios REST.
 * Esta clase está anotada como {@code @Named} y {@code @ViewScoped} para ser
 * utilizada en el contexto de JSF (JavaServer Faces).
 */
@Named(value = "problemabean")
@ViewScoped
public class ProblemaBean implements Serializable {

    /** Versión serial para la serialización de la clase. */
    private static final long serialVersionUID = 1L;

    /** Nombre del problema a crear o editar. */
    private String nombre;

    /** Dificultad numérica del problema (ej. 1 para fácil, 2 para medio, etc.). */
    private int dificultad;

    /** Tema o categoría asociada al problema (ej. Grafos, Programación Dinámica). */
    private String tema;

    /** Juez o plataforma de origen del problema (ej. UVA, Codeforces, SPOJ). */
    private String juez;

    /** Lista de problemas cargados desde el servicio REST. */
    private ArrayList<Problema> problemas = new ArrayList<>();

    /**
     * Constructor por defecto.
     * Inicia la carga inicial de problemas al instanciar el bean.
     */
    public ProblemaBean() {
        cargarProblemas();
    }

    /**
     * Recupera todos los problemas disponibles desde el servicio REST y los almacena
     * en la lista local {@code problemas}.
     */
    public void cargarProblemas() {
        problemas = ProblemaService.doGetAll("http://localhost:8081/problema/getall");
    }

    /**
     * Construye un objeto JSON con los datos del formulario y solicita la creación
     * de un nuevo problema mediante el servicio REST. Tras la creación exitosa,
     * recarga la lista de problemas y limpia los campos del formulario.
     */
    public void crearProblema() {
        String json = "{";
        json += "\"nombre\":\"" + nombre + "\",";
        json += "\"dificultad\":" + dificultad + ",";
        json += "\"tema\":\"" + tema + "\",";
        json += "\"juez\":\"" + juez + "\"}";

        String respuesta = ProblemaService.doPostJson(json);
        String[] data = respuesta.split("\n");
        showStickyLogin(data[0], data[1]);
        cargarProblemas();
        nombre = "";
        dificultad = 0;
        tema = "";
        juez = "";
    }

    /**
     * Elimina un problema específico invocando el servicio REST correspondiente.
     * Actualiza la lista local de problemas tras la eliminación.
     *
     * @param problema El problema que se desea eliminar.
     */
    public void eliminarProblema(Problema problema) {
        String respuesta = ProblemaService.doDelete(
            "http://localhost:8081/problema/eliminar?id=" + problema.getId()
        );
        String[] data = respuesta.split("\n");
        if (data[0].equals("204")) {
            showStickyLogin(data[0], "problema eliminado");
            problemas.remove(problema);
            cargarProblemas();
            return;
        } else {
            showStickyLogin(data[0], "no se ha podido eliminar el problema.");
            cargarProblemas();
            return;
        }
    }

    /**
     * Muestra mensajes contextualizados en la interfaz de usuario según el código
     * de estado devuelto por el servicio REST.
     *
     * @param code    Código de estado HTTP (ej. 201, 204, 406).
     * @param content Mensaje adicional para mostrar al usuario.
     */
    public void showStickyLogin(String code, String content) {
        if (code.equals("201")) {
            FacesContext.getCurrentInstance().addMessage(
                "sticky-key",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado el problema")
            );
        } else if (code.equals("406")) {
            FacesContext.getCurrentInstance().addMessage(
                "sticky-key",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content)
            );
        } else if (code.equals("204")) {
            FacesContext.getCurrentInstance().addMessage(
                "sticky-key",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha eliminado el problema")
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(
                "sticky-key",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Crítico", "Error, comuníquese con el administrador")
            );
        }
    }

    /**
     * Obtiene el nombre del problema.
     *
     * @return El nombre del problema.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del problema.
     *
     * @param nombre El nombre del problema a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nivel de dificultad del problema.
     *
     * @return La dificultad numérica del problema.
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * Establece el nivel de dificultad del problema.
     *
     * @param dificultad La dificultad numérica a establecer.
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Obtiene el tema asociado al problema.
     *
     * @return El tema del problema.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Establece el tema asociado al problema.
     *
     * @param tema El tema del problema a establecer.
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Obtiene el juez o plataforma asociada al problema.
     *
     * @return La plataforma de origen del problema.
     */
    public String getJuez() {
        return juez;
    }

    /**
     * Establece el juez o plataforma asociada al problema.
     *
     * @param juez La plataforma de origen a establecer.
     */
    public void setJuez(String juez) {
        this.juez = juez;
    }

    /**
     * Obtiene la lista de problemas cargados.
     *
     * @return La lista de problemas.
     */
    public ArrayList<Problema> getProblemas() {
        return problemas;
    }

    /**
     * Establece la lista de problemas.
     *
     * @param problemas La lista de problemas a establecer.
     */
    public void setProblemas(ArrayList<Problema> problemas) {
        this.problemas = problemas;
    }

    /**
     * Verifica si el usuario autenticado tiene permisos de administrador o profesor.
     *
     * @return {@code true} si el usuario es {@code Admin} o {@code Profesor}, {@code false} en caso contrario.
     */
    public boolean getAdminIniciada() {
        if (UsuarioActual.getUsuarioActual() == null) {
            return false;
        }
        return UsuarioActual.getUsuarioActual() instanceof Admin
                || UsuarioActual.getUsuarioActual() instanceof Profesor;
    }
}
