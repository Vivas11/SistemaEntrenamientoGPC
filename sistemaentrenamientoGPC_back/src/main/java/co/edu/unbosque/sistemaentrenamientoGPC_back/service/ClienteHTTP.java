package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.time.Duration;

import com.google.gson.GsonBuilder;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EjercicioDTO;



public class ClienteHTTP {
	
	
	
	private static final HttpClient CLIENTE = HttpClient.newBuilder().version(Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();
	
	
//	public static String doGetEjercicio(String url) {
		
//		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
//				.header("Content_Type", "aplication-json").build();
//		HttpResponse<String> respuesta = null;
//		try {
//			respuesta = CLIENTE.send(solicitud, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException e) {
//			System.out.println("Error al solicitar");
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			System.out.println("Error de interrupción de ka comunicación");
//			e.printStackTrace();
//		}
//		System.out.println("Codigo de repuesta de la solicitud " + respuesta.statusCode());
//		String json = respuesta.body();
//		EjercicioDTO chisteDTO = new GsonBuilder().create().fromJson(json, EjercicioDTO.class);
//		chisteDTO.setEstado(respuesta.statusCode());
//		return chisteDTO;
//		
		
//	}


}
