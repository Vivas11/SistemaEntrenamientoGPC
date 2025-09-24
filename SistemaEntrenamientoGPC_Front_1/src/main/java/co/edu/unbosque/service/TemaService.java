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

import co.edu.unbosque.model.Tema;

/**
 * Servicio REST para operaciones sobre temas (listar, crear y eliminar).
 */
public class TemaService {
	/** Cliente HTTP con timeout de 15s. */
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(15))
			.build();

	/**
	 * Recupera todos los temas.
	 * @param url Endpoint de listado.
	 * @return Lista de temas o vacía en error.
	 */
	public static ArrayList<Tema> doGetAll(String url) {
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
		System.out.println("get status code -> " + response.statusCode());
		String json = response.body();
		Tema[] temps = new Gson().fromJson(json, Tema[].class);
		if (temps == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(temps));
	}

	/**
	 * Crea un nuevo tema mediante JSON.
	 * @param json Representación JSON del tema.
	 * @return Status code y body, o código de error simbólico.
	 */
	public static String doPostJson(String json) {
		String url = "http://localhost:8081/tema/createjson";
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(json))
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
		return response.statusCode() + "\n" + response.body();
	}

	/**
	 * Elimina un tema mediante DELETE.
	 * @param url Endpoint DELETE.
	 * @return Status code y body o código simbólico en caso de fallo.
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
}
