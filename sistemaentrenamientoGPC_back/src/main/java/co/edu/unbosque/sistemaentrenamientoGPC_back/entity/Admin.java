package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;


import jakarta.persistence.Entity;

@Entity

public class Admin extends Usuario{

	public Admin(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
	
	

}
