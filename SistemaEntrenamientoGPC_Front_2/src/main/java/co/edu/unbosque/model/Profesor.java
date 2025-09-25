package co.edu.unbosque.model;

import java.util.Objects;

/**
 * Clase que representa a un profesor perteneciente al GPC (Grupo de Programación Competitiva).
 * Hereda los atributos y comportamientos básicos de la clase {@link Usuario}.
 */
public class Profesor extends Usuario {

    /** Cargo que ocupa el profesor dentro del GPC o la institución. */
    private String cargo;

    /** Indica si el profesor es entrenador del grupo de programación competitiva. */
    private boolean esEntrenador;

    /**
     * Constructor vacío de la clase Profesor.
     * Se utiliza principalmente para la inicialización por defecto o frameworks que requieren un constructor sin parámetros.
     */
    public Profesor() {
        super();
    }

    /**
     * Constructor que inicializa los atributos específicos de la clase Profesor.
     *
     * @param cargo         Cargo que ocupa el profesor.
     * @param esEntrenador  Indica si el profesor es entrenador.
     */
    public Profesor(String cargo, boolean esEntrenador) {
        super();
        this.cargo = cargo;
        this.esEntrenador = esEntrenador;
    }

    /**
     * Constructor que inicializa los atributos heredados de {@link Usuario} y los específicos de Profesor.
     *
     * @param nombre        Nombre del profesor.
     * @param correo        Correo electrónico del profesor.
     * @param edad          Edad del profesor.
     * @param contrasena    Contraseña del profesor.
     * @param cargo         Cargo que ocupa el profesor.
     * @param esEntrenador  Indica si el profesor es entrenador.
     */
    public Profesor(String nombre, String correo, int edad, String contrasena, String cargo, boolean esEntrenador) {
        super(nombre, correo, edad, contrasena);
        this.cargo = cargo;
        this.esEntrenador = esEntrenador;
    }

    /**
     * Constructor que inicializa solo los atributos heredados de {@link Usuario}.
     * Los atributos específicos de Profesor se inicializan con valores por defecto.
     *
     * @param nombre        Nombre del profesor.
     * @param correo        Correo electrónico del profesor.
     * @param edad          Edad del profesor.
     * @param contrasena    Contraseña del profesor.
     */
    public Profesor(String nombre, String correo, int edad, String contrasena) {
        super(nombre, correo, edad, contrasena);
    }

    /**
     * Genera un código hash para el objeto Profesor basado en sus atributos y los de la clase padre.
     *
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(cargo, esEntrenador);
        return result;
    }

    /**
     * Compara este objeto Profesor con otro para determinar si son iguales.
     * Dos profesores son iguales si sus atributos (incluyendo los heredados) son iguales.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profesor other = (Profesor) obj;
        return Objects.equals(cargo, other.cargo) && esEntrenador == other.esEntrenador;
    }

    /**
     * Obtiene el cargo del profesor.
     *
     * @return Cargo del profesor.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Establece el cargo del profesor.
     *
     * @param cargo Cargo del profesor.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Indica si el profesor es entrenador.
     *
     * @return {@code true} si el profesor es entrenador, {@code false} en caso contrario.
     */
    public boolean isEsEntrenador() {
        return esEntrenador;
    }

    /**
     * Establece si el profesor es entrenador.
     *
     * @param esEntrenador {@code true} si el profesor es entrenador, {@code false} en caso contrario.
     */
    public void setEsEntrenador(boolean esEntrenador) {
        this.esEntrenador = esEntrenador;
    }

    /**
     * Devuelve una representación en cadena del objeto Profesor, incluyendo sus atributos específicos.
     *
     * @return Cadena que representa el objeto Profesor.
     */
    @Override
    public String toString() {
        return "Profesor [cargo=" + cargo + ", esEntrenador=" + esEntrenador + "]";
    }
}
