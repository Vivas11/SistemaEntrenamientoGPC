package co.edu.unbosque.model;

import java.util.Objects;
import jakarta.persistence.Entity;

/**
 * Entidad que representa un problema de programación con sus atributos principales:
 * dificultad, tema y juez (plataforma) asociado.
 */
@Entity
public class Problema {

    /** Identificador único del problema. */
    private Long id;

    /** Nombre descriptivo del problema. */
    private String nombre;

    /** Dificultad del problema (por ejemplo: Fácil, Medio, Difícil). */
    private String dificultad;

    /** Tema o categoría principal del problema (ej. Grafos, Programación Dinámica). */
    private String tema;

    /** Juez o plataforma de origen del problema (ej. UVA, Codeforces, SPOJ). */
    private String juez;

    /**
     * Constructor vacío requerido por frameworks como JPA.
     */
    public Problema() {
    }

    /**
     * Constructor completo para inicializar un problema con todos sus atributos.
     *
     * @param id          Identificador único del problema.
     * @param nombre      Nombre descriptivo del problema.
     * @param dificultad  Dificultad textual del problema.
     * @param tema        Tema o categoría principal del problema.
     * @param juez        Juez o plataforma de origen del problema.
     */
    public Problema(Long id, String nombre, String dificultad, String tema, String juez) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.tema = tema;
        this.juez = juez;
    }

    /**
     * Genera un código hash para el objeto Problema basado en sus atributos.
     *
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dificultad, id, juez, nombre, tema);
    }

    /**
     * Compara este objeto Problema con otro para determinar si son iguales.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Problema other = (Problema) obj;
        return Objects.equals(dificultad, other.dificultad)
                && Objects.equals(id, other.id)
                && Objects.equals(juez, other.juez)
                && Objects.equals(nombre, other.nombre)
                && Objects.equals(tema, other.tema);
    }

    /**
     * Obtiene el nombre del problema.
     *
     * @return Nombre del problema.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del problema.
     *
     * @param nombre Nombre del problema.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dificultad del problema.
     *
     * @return Dificultad textual del problema.
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Establece la dificultad del problema.
     *
     * @param dificultad Dificultad textual del problema.
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Obtiene el tema o categoría principal del problema.
     *
     * @return Tema asociado al problema.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Establece el tema o categoría principal del problema.
     *
     * @param tema Tema asociado al problema.
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Obtiene el juez o plataforma de origen del problema.
     *
     * @return Juez/plataforma de origen del problema.
     */
    public String getJuez() {
        return juez;
    }

    /**
     * Establece el juez o plataforma de origen del problema.
     *
     * @param juez Juez/plataforma de origen del problema.
     */
    public void setJuez(String juez) {
        this.juez = juez;
    }

    /**
     * Obtiene el identificador único del problema.
     *
     * @return Identificador del problema.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del problema.
     *
     * @param id Identificador del problema.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve una representación en cadena del objeto Problema.
     *
     * @return Cadena que representa el objeto Problema.
     */
    @Override
    public String toString() {
        return "Problema [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + ", id=" + id + "]";
    }
}
