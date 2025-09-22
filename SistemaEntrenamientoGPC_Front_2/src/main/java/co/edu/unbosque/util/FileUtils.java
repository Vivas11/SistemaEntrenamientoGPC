package co.edu.unbosque.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
	
	 public static String guardarPDF(byte[] bytes, String nombreArchivo) throws IOException {
	        // Ruta dentro de tu servidor
	        String ruta = "C:/server/uploads/" + nombreArchivo; // Linux
	        // Ej: en Windows podría ser: "C:/server/uploads/" + nombreArchivo;

	        try (FileOutputStream fos = new FileOutputStream(ruta)) {
	            fos.write(bytes);
	        }

	        return nombreArchivo; // devuelves el nombre (lo guardas en la BD)
	    }
	 
}