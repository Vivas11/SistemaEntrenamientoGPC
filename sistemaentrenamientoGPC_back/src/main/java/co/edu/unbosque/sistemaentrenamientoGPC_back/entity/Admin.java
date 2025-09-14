package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import jakarta.persistence.Entity;

@Entity

public class Admin extends Usuario {

	/**
	 * Constructor vacio de la clase Admin
	 */
	public Admin() {
	}

	/**
	 * Constructor con parametros (atributos) heredados de Usuario
	 * 
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 */
	public Admin(String nombre, String correo, int edad, String contrasena) {
		super(nombre, correo, edad, contrasena);
		// TODO Auto-generated constructor stub
	}

}
