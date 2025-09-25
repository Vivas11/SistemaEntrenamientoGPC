package co.edu.unbosque.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import co.edu.unbosque.model.Estudiante;

/**
 * Servicio para consumir endpoints REST relacionados con estudiantes.
 * Provee operaciones de creación (POST), consulta (GET) y eliminación (DELETE).
 */
public class EstudianteService {
	/** Cliente HTTP reutilizable con timeout configurado. */
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(5))
			.build();

	/**
	 * Envía un JSON al endpoint de creación de estudiante.
	 * @param json Representación JSON del estudiante a crear.
	 * @return Status code y body separados por salto de línea; códigos de error simbólicos en caso de fallo.
	 */
	public static String doPostJson(String json) {
		String url = "http://localhost:8081/estudiante/createjson";
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return "ERROR_INTERRUPTED";
		} catch (IOException e) {
			return "ERROR_IO: " + e.getMessage();
		}
		return response.statusCode() + "\n" + response.body();
	}

	/**
	 * Recupera todos los estudiantes.
	 * @param url Endpoint completo (ej: http://localhost:8081/estudiante/all).
	 * @return Lista de estudiantes, o vacía en error.
	 */
	public static ArrayList<Estudiante> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return new ArrayList<>();
		} catch (IOException e) {
			return new ArrayList<>();
		}
		if (response == null) {
			return new ArrayList<>();
		}
		System.out.println("get status code -> " + response.statusCode());
		String body = response.body();
		Gson g = new Gson();
		Estudiante[] temps = g.fromJson(body, Estudiante[].class);
		if (temps == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(temps));
	}

	/**
	 * Ejecuta una eliminación lógica/física vía DELETE.
	 * @param url Endpoint DELETE.
	 * @return Status code y body o código simbólico de error.
	 */
	public static String doDelete(String url) {
		HttpRequest request = HttpRequest.newBuilder()
				.DELETE()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response = null;
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
}
