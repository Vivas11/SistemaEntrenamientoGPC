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

public class ProblemaService {
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(15)).build();
	
	public static ArrayList<Problema> doGetAll(String url) {
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
		Problema[] temps = g.fromJson(json, Problema[].class);
		if (temps == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(temps));

	}
	
	public static String doPostJson(String json) {
		String url = "http://localhost:8081/problema/createjson";
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response.statusCode() + "\n" + response.body();
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
}
