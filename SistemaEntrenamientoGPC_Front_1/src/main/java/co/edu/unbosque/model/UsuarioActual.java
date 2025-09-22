package co.edu.unbosque.model;

public class UsuarioActual {
	private static Usuario usuarioActual;

	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public static void setUsuarioActual(Usuario usuarioActual) {
		UsuarioActual.usuarioActual = usuarioActual;
	}
	
	
}
