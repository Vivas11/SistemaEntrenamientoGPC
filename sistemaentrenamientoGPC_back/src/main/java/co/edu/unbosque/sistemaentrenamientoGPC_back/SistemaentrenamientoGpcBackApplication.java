package co.edu.unbosque.sistemaentrenamientoGPC_back;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaentrenamientoGpcBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaentrenamientoGpcBackApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
