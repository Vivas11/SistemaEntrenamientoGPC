package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

public class Admin extends Usuario{
	
	
	private String tipoAdmin;
	
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}


	public Admin(String tipoAdmin) {
		super();
		this.tipoAdmin = tipoAdmin;
	}


	public Admin(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tipoAdmin);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(tipoAdmin, other.tipoAdmin);
	}


	public String getTipoAdmin() {
		return tipoAdmin;
	}


	public void setTipoAdmin(String tipoAdmin) {
		this.tipoAdmin = tipoAdmin;
	}


	@Override
	public String toString() {
		return "Admin [tipoAdmin=" + tipoAdmin + "]";
	}
	
	
	

}
