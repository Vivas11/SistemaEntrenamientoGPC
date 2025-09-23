package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Arrays;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Doc;

public class DocPDFDTO extends Doc {

	private byte[] contenidoPdf;

	public DocPDFDTO() {
	}

	public DocPDFDTO(Long id, byte[] contenidoPdf) {
		super();
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDFDTO(String nombre, String descripcion, byte[] imagen,  byte[] contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDFDTO(String nombre, String descripcion, byte[] imagen) {
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
		DocPDFDTO other = (DocPDFDTO) obj;
		return Arrays.equals(contenidoPdf, other.contenidoPdf);
	}


}
