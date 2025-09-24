package co.edu.unbosque.model;

public class UsuarioActual {
	private static Usuario usuarioActual;

	public static Usuario getUsuarioActual() {
		if (usuarioActual == null) {
			return null;
		}
		return usuarioActual;
	}

	public static void setUsuarioActual(Usuario usuarioActual) {
		UsuarioActual.usuarioActual = usuarioActual;
	}
}
