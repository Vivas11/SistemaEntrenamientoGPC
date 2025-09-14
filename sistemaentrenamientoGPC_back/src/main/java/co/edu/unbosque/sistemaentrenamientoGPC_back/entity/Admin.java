package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;


import jakarta.persistence.Entity;

@Entity

public class Admin extends Usuario{

	
	public Admin() {
	}
	
	
	public Admin(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
	}

	

	
	
	
	
	

}
