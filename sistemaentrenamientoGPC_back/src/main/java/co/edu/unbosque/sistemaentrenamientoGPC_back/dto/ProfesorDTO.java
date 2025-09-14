package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

public class ProfesorDTO extends Usuario{
	
	
	private String cargo;
	private boolean esEntrnado;
	
	public ProfesorDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProfesorDTO(String cargo, boolean esEntrnado) {
		super();
		this.cargo = cargo;
		this.esEntrnado = esEntrnado;
	}

	public ProfesorDTO(String nombre, String correo, int edad, String cargo, boolean esEntrnado) {
		super(nombre, correo, edad);
		this.cargo = cargo;
		this.esEntrnado = esEntrnado;
	}

	public ProfesorDTO(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cargo, esEntrnado);
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
		ProfesorDTO other = (ProfesorDTO) obj;
		return Objects.equals(cargo, other.cargo) && esEntrnado == other.esEntrnado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isEsEntrnado() {
		return esEntrnado;
	}

	public void setEsEntrnado(boolean esEntrnado) {
		this.esEntrnado = esEntrnado;
	}

	@Override
	public String toString() {
		return "ProfesorDTO [cargo=" + cargo + ", esEntrnado=" + esEntrnado + "]";
	}
	
	
	
	
	

}
