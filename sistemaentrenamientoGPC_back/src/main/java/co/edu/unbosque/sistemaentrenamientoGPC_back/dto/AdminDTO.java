package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;


import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

public class AdminDTO extends Usuario {
	
	/**
	 * Constructor vacio de la clase AdminDTO
	 */
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parametros (atributos) heredados de Usuario
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 */
	public AdminDTO(String nombre, String correo, int edad, String contrasena) {
		super(nombre, correo, edad, contrasena);
	}
	
}
