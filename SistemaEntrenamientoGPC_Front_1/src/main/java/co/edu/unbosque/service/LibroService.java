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

public class LibroService {
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(15)).build();

	public static ArrayList<DocPDF> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("get status code -> " + response.statusCode());
		String json = response.body();
		Gson g = new Gson();
		DocPDF[] temps = g.fromJson(json, DocPDF[].class);
		return new ArrayList<>(Arrays.asList(temps));

	}

	public static String doDelete(String url) {
		HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt(); // buena práctica si es interrumpido
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("delete status code -> " + response.statusCode());

		return response.statusCode() + "\n" + response.body();
	}
	

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

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}

	// Igual que antes: concatenador de bytes
	private static byte[] concat(byte[]... arrays) {
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
