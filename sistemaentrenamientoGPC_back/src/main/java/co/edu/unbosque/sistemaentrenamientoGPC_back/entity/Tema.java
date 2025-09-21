package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Tema {
	
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = false, name = "tema")
	private String tema;
	
	@Column(unique = false, name = "tipo")
	private String tipo; 

	@Column(unique = false, name = "contenido")
	private String contenido ;
	
	
     public Tema() {
	// TODO Auto-generated constructor stub
   }


	 


	 public Tema(String tema, String tipo, String contenido) {
		super();
		this.tema = tema;
		this.tipo = tipo;
		this.contenido = contenido;
	}
	 
	 
	 








	 @Override
	public int hashCode() {
		return Objects.hash(contenido, id, tema, tipo);
	}





	 @Override
	 public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(id, other.id)
				&& Objects.equals(tema, other.tema) && Objects.equals(tipo, other.tipo);
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
	 


	 public Long getId() {
		return id;
	}





	 public void setId(Long id) {
		 this.id = id;
	 }





	 @Override
	 public String toString() {
		return "Tema [tema=" + tema + ", tipo=" + tipo + ", contenido=" + contenido + "]";
	 }
     

 

}
