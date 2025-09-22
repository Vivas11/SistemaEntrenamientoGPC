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

public class EventoService {
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(5)).build();

	public static ArrayList<Evento> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		if (response == null) {
			return new ArrayList<>();
		}

		System.out.println("get status code -> " + response.statusCode());
		String json = response.body();

		// Gson con soporte para LocalDate
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

		Evento[] eventos = gson.fromJson(json, Evento[].class);
		return new ArrayList<>(Arrays.asList(eventos));
	}
}
