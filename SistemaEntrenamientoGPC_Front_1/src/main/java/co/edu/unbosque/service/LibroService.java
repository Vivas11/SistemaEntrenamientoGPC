package co.edu.unbosque.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.primefaces.model.file.UploadedFile;

import com.google.gson.Gson;

import co.edu.unbosque.model.DocPDF;

/**
 * Servicio para operaciones REST sobre libros/documentos PDF. Incluye:
 * <ul>
 *   <li>Listado de documentos (GET)</li>
 *   <li>Eliminación (DELETE)</li>
 *   <li>Subida multipart de metadatos + imagen + PDF (POST multipart)</li>
 * </ul>
 */
public class LibroService {
	/** Cliente HTTP con timeout extendido (15s) para cargas de archivo. */
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(15))
			.build();

	/**
	 * Obtiene todos los documentos PDF.
	 * @param url Endpoint completo de listado.
	 * @return Lista de documentos o vacía en error.
	 */
	public static ArrayList<DocPDF> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return new ArrayList<>();
		} catch (IOException e) {
			return new ArrayList<>();
		}
		System.out.println("get status code -> " + (response != null ? response.statusCode() : "null"));
		if (response == null) {
			return new ArrayList<>();
		}
		String json = response.body();
		DocPDF[] temps = new Gson().fromJson(json, DocPDF[].class);
		if (temps == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(temps));
	}

	/**
	 * Elimina un documento en el endpoint indicado.
	 * @param url Endpoint DELETE.
	 * @return Status code y body; errores simbólicos en fallo.
	 */
	public static String doDelete(String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.DELETE()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return "ERROR_INTERRUPTED";
		} catch (IOException e) {
			return "ERROR_IO: " + e.getMessage();
		}
		System.out.println("delete status code -> " + response.statusCode());
		return response.statusCode() + "\n" + response.body();
	}

	/**
	 * Realiza una subida multipart de un libro (nombre, descripción, imagen y PDF).
	 * Construye manualmente el cuerpo multipart.
	 * @param url Endpoint de subida.
	 * @param nombre Nombre del documento.
	 * @param descripcion Descripción del documento.
	 * @param imagen Archivo de imagen (carátula).
	 * @param archivoPdf Archivo PDF.
	 * @return Status code y body, o mensaje de error.
	 */
	public static String doPostMultipart(String url, String nombre, String descripcion,
	                                     UploadedFile imagen, UploadedFile archivoPdf) {
		String boundary = "----JavaBoundary" + System.currentTimeMillis();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("--").append(boundary).append("\r\n");
			sb.append("Content-Disposition: form-data; name=\"nombre\"\r\n\r\n");
			sb.append(nombre).append("\r\n");

			sb.append("--").append(boundary).append("\r\n");
			sb.append("Content-Disposition: form-data; name=\"descripcion\"\r\n\r\n");
			sb.append(descripcion).append("\r\n");

			byte[] imageBytes = imagen.getContent();
			String imagePartHeader = "--" + boundary + "\r\n"
					+ "Content-Disposition: form-data; name=\"imagen\"; filename=\"" + imagen.getFileName() + "\"\r\n"
					+ "Content-Type: " + imagen.getContentType() + "\r\n\r\n";

			byte[] pdfBytes = archivoPdf.getContent();
			String pdfPartHeader = "--" + boundary + "\r\n"
					+ "Content-Disposition: form-data; name=\"archivoPdf\"; filename=\"" + archivoPdf.getFileName() + "\"\r\n"
					+ "Content-Type: " + archivoPdf.getContentType() + "\r\n\r\n";

			String closing = "\r\n--" + boundary + "--\r\n";

			byte[] requestBody = concat(
					sb.toString().getBytes(),
					imagePartHeader.getBytes(), imageBytes, "\r\n".getBytes(),
					pdfPartHeader.getBytes(), pdfBytes, closing.getBytes()
			);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "multipart/form-data; boundary=" + boundary)
					.POST(HttpRequest.BodyPublishers.ofByteArray(requestBody))
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("post status code -> " + response.statusCode());
			return response.statusCode() + "\n" + response.body();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return "ERROR_INTERRUPTED";
		} catch (IOException e) {
			return "ERROR_IO: " + e.getMessage();
		} catch (Exception e) {
			return "ERROR: " + e.getMessage();
		}
	}

	/**
	 * Concatena múltiples arreglos de bytes en uno solo.
	 * @param arrays Arreglos a unir.
	 * @return Nuevo arreglo combinado.
	 */
	public static byte[] concat(byte[]... arrays) {
		int totalLength = 0;
		for (byte[] arr : arrays) {
			totalLength += arr.length;
		}
		byte[] result = new byte[totalLength];
		int pos = 0;
		for (byte[] arr : arrays) {
			System.arraycopy(arr, 0, result, pos, arr.length);
			pos += arr.length;
		}
		return result;
	}
}
