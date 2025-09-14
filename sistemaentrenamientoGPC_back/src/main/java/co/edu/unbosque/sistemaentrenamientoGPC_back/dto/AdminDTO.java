package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

public class AdminDTO extends Usuario {
	
	private String tipoAdmin;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminDTO(String tipoAdmin) {
		super();
		this.tipoAdmin = tipoAdmin;
	}

	public AdminDTO(String nombre, String correo, int edad, String tipoAdmin) {
		super(nombre, correo, edad);
		this.tipoAdmin = tipoAdmin;
	}

	public AdminDTO(String nombre, String correo, int edad) {
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
		AdminDTO other = (AdminDTO) obj;
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
		return "AdminDTO [tipoAdmin=" + tipoAdmin + "]";
	}
	
	
	

}
