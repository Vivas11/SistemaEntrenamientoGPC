package co.edu.unbosque.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("juegoBean")
@ViewScoped
public class JuegoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String respuesta;

	private boolean mostrarJuego1 = true;
	private boolean mostrarJuego2 = false;
	private boolean mostrarJuego3 = false;
	private boolean mostrarJuego4 = false;
	private boolean mostrarJuego5 = false;
	private boolean mostrarFinal = false;

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

	private void addInfo(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	private void addError(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isMostrarJuego1() {
		return mostrarJuego1;
	}

	public boolean isMostrarJuego2() {
		return mostrarJuego2;
	}

	public boolean isMostrarJuego3() {
		return mostrarJuego3;
	}

	public boolean isMostrarJuego4() {
		return mostrarJuego4;
	}

	public boolean isMostrarJuego5() {
		return mostrarJuego5;
	}

	public boolean isMostrarFinal() {
		return mostrarFinal;
	}
}
