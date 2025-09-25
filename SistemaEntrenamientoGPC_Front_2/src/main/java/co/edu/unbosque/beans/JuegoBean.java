package co.edu.unbosque.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Bean de respaldo para la lógica del juego interactivo que consiste en una
 * serie de preguntas. Controla la respuesta del usuario, el avance entre juegos
 * y muestra mensajes de retroalimentación.
 */
@Named("juegoBean")
@ViewScoped
public class JuegoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Respuesta actual del usuario */
	private String respuesta;

	/** Variables para controlar la visibilidad de cada etapa del juego */
	private boolean mostrarJuego1 = true;
	private boolean mostrarJuego2 = false;
	private boolean mostrarJuego3 = false;
	private boolean mostrarJuego4 = false;
	private boolean mostrarJuego5 = false;
	private boolean mostrarFinal = false;

	/**
	 * Valida la respuesta del Juego 1. La respuesta correcta es "0". Si es
	 * correcta, avanza al juego 2 y limpia la respuesta. Si es incorrecta, muestra
	 * pista de error.
	 */
	public void validarJuego1() {
		if ("0".equals(respuesta.trim())) {
			mostrarJuego1 = false;
			mostrarJuego2 = true;
			respuesta = "";
			addInfo("✅ ¡Correcto! Los programadores empiezan a contar desde 0.");
		} else {
			addError("❌ Pista: no es el 1...");
		}
	}

	/**
	 * Valida la respuesta del Juego 2. La respuesta correcta es "java" (sin
	 * importar mayúsculas/minúsculas). Si es correcta, avanza al juego 3 y limpia
	 * la respuesta. Si es incorrecta, muestra pista de error.
	 */
	public void validarJuego2() {
		if ("java".equalsIgnoreCase(respuesta.trim())) {
			mostrarJuego2 = false;
			mostrarJuego3 = true;
			respuesta = "";
			addInfo("✅ Exacto, Java ☕.");
		} else {
			addError("❌ Ups, piensa en café...");
		}
	}

	/**
	 * Valida la respuesta del Juego 3. La respuesta correcta es "6". Si es
	 * correcta, avanza al juego 4 y limpia la respuesta. Si es incorrecta, muestra
	 * mensaje de error.
	 */
	public void validarJuego3() {
		if ("6".equals(respuesta.trim())) {
			mostrarJuego3 = false;
			mostrarJuego4 = true;
			respuesta = "";
			addInfo("✅ Bien hecho, sabes sumar en código.");
		} else {
			addError("❌ No es correcto, inténtalo otra vez.");
		}
	}

	/**
	 * Valida la respuesta del Juego 4. La respuesta correcta es "bucle" o "loop".
	 * Si es correcta, avanza al juego 5 y limpia la respuesta. Si es incorrecta,
	 * muestra pista de error.
	 */
	public void validarJuego4() {
		if ("bucle".equalsIgnoreCase(respuesta.trim()) || "loop".equalsIgnoreCase(respuesta.trim())) {
			mostrarJuego4 = false;
			mostrarJuego5 = true;
			respuesta = "";
			addInfo("✅ Correcto, los bucles repiten acciones.");
		} else {
			addError("❌ Pista: empieza con B...");
		}
	}

	/**
	 * Valida la respuesta del Juego 5. La respuesta correcta es "scratch". Si es
	 * correcta, muestra la pantalla final y limpia la respuesta. Si es incorrecta,
	 * muestra pista de error.
	 */
	public void validarJuego5() {
		if ("scratch".equalsIgnoreCase(respuesta.trim())) {
			mostrarJuego5 = false;
			mostrarFinal = true;
			respuesta = "";
			addInfo("🎉 ¡Lo lograste! Scratch es el amigo programador.");
		} else {
			addError("❌ Piensa en el gato naranja...");
		}
	}

	/**
	 * Muestra un mensaje de información tipo INFO en la interfaz.
	 * 
	 * @param msg Mensaje a mostrar
	 */
	private void addInfo(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	/**
	 * Muestra un mensaje de error en la interfaz.
	 * 
	 * @param msg Mensaje de error a mostrar
	 */
	private void addError(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	/**
	 * Obtiene la respuesta actual del usuario.
	 * 
	 * @return Respuesta como String
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Establece la respuesta actual del usuario.
	 * 
	 * @param respuesta Texto ingresado por el usuario
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Indica si se debe mostrar la pantalla del Juego 1.
	 * 
	 * @return true si la pantalla del Juego 1 está visible, false en caso
	 *         contrario.
	 */
	public boolean isMostrarJuego1() {
		return mostrarJuego1;
	}

	/**
	 * Indica si se debe mostrar la pantalla del Juego 2.
	 * 
	 * @return true si la pantalla del Juego 2 está visible, false en caso
	 *         contrario.
	 */
	public boolean isMostrarJuego2() {
		return mostrarJuego2;
	}

	/**
	 * Indica si se debe mostrar la pantalla del Juego 3.
	 * 
	 * @return true si la pantalla del Juego 3 está visible, false en caso
	 *         contrario.
	 */
	public boolean isMostrarJuego3() {
		return mostrarJuego3;
	}

	/**
	 * Indica si se debe mostrar la pantalla del Juego 4.
	 * 
	 * @return true si la pantalla del Juego 4 está visible, false en caso
	 *         contrario.
	 */
	public boolean isMostrarJuego4() {
		return mostrarJuego4;
	}

	/**
	 * Indica si se debe mostrar la pantalla del Juego 5.
	 * 
	 * @return true si la pantalla del Juego 5 está visible, false en caso
	 *         contrario.
	 */
	public boolean isMostrarJuego5() {
		return mostrarJuego5;
	}

	/**
	 * Indica si se debe mostrar la pantalla final del juego.
	 * 
	 * @return true si la pantalla final está visible, false en caso contrario.
	 */
	public boolean isMostrarFinal() {
		return mostrarFinal;
	}

	/**
	 * Establece el valor de mostrarJuego1, que indica si se debe mostrar la pantalla del Juego 1.
	 * @param mostrarJuego1 true para mostrar el Juego 1, false para ocultarlo
	 */
	public void setMostrarJuego1(boolean mostrarJuego1) {
	    this.mostrarJuego1 = mostrarJuego1;
	}

	/**
	 * Establece el valor de mostrarJuego2, que indica si se debe mostrar la pantalla del Juego 2.
	 * @param mostrarJuego2 true para mostrar el Juego 2, false para ocultarlo
	 */
	public void setMostrarJuego2(boolean mostrarJuego2) {
	    this.mostrarJuego2 = mostrarJuego2;
	}

	/**
	 * Establece el valor de mostrarJuego3, que indica si se debe mostrar la pantalla del Juego 3.
	 * @param mostrarJuego3 true para mostrar el Juego 3, false para ocultarlo
	 */
	public void setMostrarJuego3(boolean mostrarJuego3) {
	    this.mostrarJuego3 = mostrarJuego3;
	}

	/**
	 * Establece el valor de mostrarJuego4, que indica si se debe mostrar la pantalla del Juego 4.
	 * @param mostrarJuego4 true para mostrar el Juego 4, false para ocultarlo
	 */
	public void setMostrarJuego4(boolean mostrarJuego4) {
	    this.mostrarJuego4 = mostrarJuego4;
	}

	/**
	 * Establece el valor de mostrarJuego5, que indica si se debe mostrar la pantalla del Juego 5.
	 * @param mostrarJuego5 true para mostrar el Juego 5, false para ocultarlo
	 */
	public void setMostrarJuego5(boolean mostrarJuego5) {
	    this.mostrarJuego5 = mostrarJuego5;
	}

	/**
	 * Establece el valor de mostrarFinal, que indica si se debe mostrar la pantalla final del juego.
	 * @param mostrarFinal true para mostrar la pantalla final, false para ocultarla
	 */
	public void setMostrarFinal(boolean mostrarFinal) {
	    this.mostrarFinal = mostrarFinal;
	}


}
