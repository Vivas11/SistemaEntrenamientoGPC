package co.edu.unbosque.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utilidades relacionadas con manejo básico de archivos para la aplicación.
 * <p>
 * Actualmente provee soporte para almacenar bytes de un PDF en una ruta fija del servidor.
 * </p>
 * <p><strong>NOTAS:</strong>
 * <ul>
 *   <li>La ruta está codificada como <em>C:/server/uploads/</em>; considerar externalizarla a configuración.</li>
 *   <li>No se valida simultaneidad ni se generan nombres únicos; el archivo puede sobrescribirse.</li>
 *   <li>No se realizan validaciones de seguridad sobre el nombre del archivo (path traversal).</li>
 *   <li>Para un entorno productivo se recomienda sanitizar el nombre, generar un UUID y restringir permisos.</li>
 * </ul>
 * </p>
 */
public class FileUtils {

	private static final String BASE_UPLOAD_PATH = "C:/server/uploads/"; // TODO: externalizar a config.

	/**
	 * Guarda un archivo PDF (o en general cualquier arreglo de bytes) en el sistema de archivos.
	 * Crea el directorio base si no existe.
	 *
	 * @param bytes         contenido del archivo
	 * @param nombreArchivo nombre con el que se almacenará (ej: documento.pdf)
	 * @return el nombre del archivo almacenado (para persistencia en BD u otra referencia)
	 * @throws IOException si ocurre un error de E/S durante la escritura
	 */
	public static String guardarPDF(byte[] bytes, String nombreArchivo) throws IOException {
		Path basePath = Paths.get(BASE_UPLOAD_PATH);
		if (Files.notExists(basePath)) {
			Files.createDirectories(basePath);
		}

		Path filePath = basePath.resolve(nombreArchivo);
		try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
			fos.write(bytes);
		}

		return nombreArchivo;
	}
}