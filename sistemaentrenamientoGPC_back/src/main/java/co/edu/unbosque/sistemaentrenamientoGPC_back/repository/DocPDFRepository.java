package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.DocPDF;

public interface DocPDFRepository extends JpaRepository<DocPDF, Long> {
	
	public Optional<DocPDF> findByNombre(String nombre);
	public void deleteByNombre(String nombre);

}