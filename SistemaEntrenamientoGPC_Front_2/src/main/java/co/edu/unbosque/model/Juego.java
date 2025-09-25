package co.edu.unbosque.model;

import java.util.Objects;

/**
 * Clase que representa un juego con múltiples etapas y la respuesta del
 * usuario. Controla la visibilidad de cada etapa del juego mediante variables
 * booleanas.
 */
public class Juego {

	/** Respuesta actual del usuario en el juego. */
	private String respuesta;

	/** Indica si se debe mostrar la primera etapa del juego. */
	private boolean mostrarJuego1;

	/** Indica si se debe mostrar la segunda etapa del juego. */
	private boolean mostrarJuego2;

	/** Indica si se debe mostrar la tercera etapa del juego. */
	private boolean mostrarJuego3;

	/** Indica si se debe mostrar la cuarta etapa del juego. */
	private boolean mostrarJuego4;

	/** Indica si se debe mostrar la quinta etapa del juego. */
	private boolean mostrarJuego5;

	/** Indica si se debe mostrar la etapa final del juego. */
	private boolean mostrarFinal;

	/**
	 * Constructor por defecto de la clase Juego.
	 */
	public Juego() {
	}

	/**
	 * Constructor parametrizado de la clase Juego.
	 *
	 * @param respuesta     Respuesta actual del usuario.
	 * @param mostrarJuego1 Indica si se debe mostrar la primera etapa del juego.
	 * @param mostrarJuego2 Indica si se debe mostrar la segunda etapa del juego.
	 * @param mostrarJuego3 Indica si se debe mostrar la tercera etapa del juego.
	 * @param mostrarJuego4 Indica si se debe mostrar la cuarta etapa del juego.
	 * @param mostrarJuego5 Indica si se debe mostrar la quinta etapa del juego.
	 * @param mostrarFinal  Indica si se debe mostrar la etapa final del juego.
	 */
	public Juego(String respuesta, boolean mostrarJuego1, boolean mostrarJuego2, boolean mostrarJuego3,
			boolean mostrarJuego4, boolean mostrarJuego5, boolean mostrarFinal) {
		super();
		this.respuesta = respuesta;
		this.mostrarJuego1 = mostrarJuego1;
		this.mostrarJuego2 = mostrarJuego2;
		this.mostrarJuego3 = mostrarJuego3;
		this.mostrarJuego4 = mostrarJuego4;
		this.mostrarJuego5 = mostrarJuego5;
		this.mostrarFinal = mostrarFinal;
	}

	/**
	 * Genera un código hash para el objeto Juego basado en sus atributos. ds
	 * 
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(mostrarFinal, mostrarJuego1, mostrarJuego2, mostrarJuego3, mostrarJuego4, mostrarJuego5,
				respuesta);
	}

	/**
	 * Compara este objeto Juego con otro para determinar si son iguales.
	 * 
	 * @param obj Objeto a comparar.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juego other = (Juego) obj;
		return mostrarFinal == other.mostrarFinal && mostrarJuego1 == other.mostrarJuego1
				&& mostrarJuego2 == other.mostrarJuego2 && mostrarJuego3 == other.mostrarJuego3
				&& mostrarJuego4 == other.mostrarJuego4 && mostrarJuego5 == other.mostrarJuego5
				&& Objects.equals(respuesta, other.respuesta);
	}

	/**
	 * Devuelve una representación en cadena del objeto Juego.
	 * 
	 * @return Cadena que representa el objeto Juego.
	 */
	@Override
	public String toString() {
		return "Juego [respuesta=" + respuesta + ", mostrarJuego1=" + mostrarJuego1 + ", mostrarJuego2=" + mostrarJuego2
				+ ", mostrarJuego3=" + mostrarJuego3 + ", mostrarJuego4=" + mostrarJuego4 + ", mostrarJuego5="
				+ mostrarJuego5 + ", mostrarFinal=" + mostrarFinal + "]";
	}
}
