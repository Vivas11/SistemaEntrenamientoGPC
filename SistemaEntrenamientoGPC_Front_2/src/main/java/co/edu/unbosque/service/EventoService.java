package co.edu.unbosque.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.unbosque.model.Evento;

/**
 * Servicio para operaciones REST sobre eventos (listar, crear y eliminar).
 * Maneja la deserialización de {@link LocalDate} mediante {@link LocalDateAdapter}.
 */
public class EventoService {
	/** Cliente HTTP reutilizable. */
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(5))
			.build();

	/**
	 * Recupera todos los eventos desde el endpoint especificado.
	 * @param url Endpoint completo (ej: http://localhost:8081/evento/all).
	 * @return Lista de eventos o vacía si hay error o no hay datos.
	 */
	public static ArrayList<Evento> doGetAll(String url) {
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
		String json = response.body();

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();

		Evento[] eventos = gson.fromJson(json, Evento[].class);
		if (eventos == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(eventos));
	}

	/**
	 * Envía un JSON para crear un nuevo evento.
	 * @param json Cuerpo JSON del evento.
	 * @return Status code y body; códigos de error simbólicos si falla.
	 */
	public static String doPostJson(String json) {
		String url = "http://localhost:8081/evento/createjson";
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
	 * Elimina un evento mediante el endpoint DELETE.
	 * @param url Endpoint DELETE.
	 * @return Status code y body; códigos simbólicos en caso de fallo.
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
