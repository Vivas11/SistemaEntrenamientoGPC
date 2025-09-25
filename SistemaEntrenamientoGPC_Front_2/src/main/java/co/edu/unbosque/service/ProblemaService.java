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

import co.edu.unbosque.model.Problema;

/**
 * Servicio REST para la entidad Problema: listar, crear y eliminar.
 */
public class ProblemaService {
	/** Cliente HTTP con timeout ampliado (15s) para operaciones potencialmente más lentas. */
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(15))
			.build();

	/**
	 * Obtiene todos los problemas desde el endpoint dado.
	 * @param url Endpoint completo.
	 * @return Lista de problemas o vacía en caso de error.
	 */
	public static ArrayList<Problema> doGetAll(String url) {
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
		Problema[] temps = new Gson().fromJson(json, Problema[].class);
		if (temps == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(temps));
	}

	/**
	 * Envía un JSON para crear un nuevo problema.
	 * @param json Representación JSON del problema.
	 * @return Status code y body o código simbólico de error.
	 */
	public static String doPostJson(String json) {
		String url = "http://localhost:8081/problema/createjson";
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
	 * Elimina un problema mediante el endpoint DELETE.
	 * @param url Endpoint DELETE.
	 * @return Status code y body; códigos de error simbólicos si falla.
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
