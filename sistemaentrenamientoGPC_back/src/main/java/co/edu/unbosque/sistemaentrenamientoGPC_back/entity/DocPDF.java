package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Arrays;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class DocPDF extends Doc {


	@Lob
	private byte[] contenidoPdf;

	public DocPDF() {
	}

	public DocPDF(Long id, byte[] contenidoPdf) {
		super();
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDF(String nombre, String descripcion, byte[] imagen,  byte[] contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDF(String nombre, String descripcion, byte[] imagen) {
		super(nombre, descripcion, imagen);
		// TODO Auto-generated constructor stub
	}


	public byte[] getContenidoPdf() {
		return contenidoPdf;
	}

	public void setContenidoPdf(byte[] contenidoPdf) {
		this.contenidoPdf = contenidoPdf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(contenidoPdf);
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
		DocPDF other = (DocPDF) obj;
		return Arrays.equals(contenidoPdf, other.contenidoPdf);
	}
	

}
