package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;



public class TemaDTO {
	
	
	private Long id;
	private String tema;
	private String tipo; 
	private String contenido ;
	
	
	
	public TemaDTO() {
		// TODO Auto-generated constructor stub
	}



	public TemaDTO(String tema, String tipo, String contenido) {
		super();
		this.tema = tema;
		this.tipo = tipo;
		this.contenido = contenido;
	}



	@Override
	public int hashCode() {
		return Objects.hash(contenido, tema, tipo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemaDTO other = (TemaDTO) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(tema, other.tema)
				&& Objects.equals(tipo, other.tipo);
	}



	public String getTema() {
		return tema;
	}



	public void setTema(String tema) {
		this.tema = tema;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getContenido() {
		return contenido;
	}



	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	@Override
	public String toString() {
		return "TemaDTO [tema=" + tema + ", tipo=" + tipo + ", contenido=" + contenido + "]";
	}
	
	

}