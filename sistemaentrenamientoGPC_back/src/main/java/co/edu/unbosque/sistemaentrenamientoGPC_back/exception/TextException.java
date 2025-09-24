package co.edu.unbosque.sistemaentrenamientoGPC_back.exception;

public class TextException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextException() {
		super ("Solo puede contener letras.");
	}
}
