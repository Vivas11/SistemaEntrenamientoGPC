package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.DocPDF;

/**
 * Repositorio JPA para la entidad {@code DocPDF}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre documentos PDF.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface DocPDFRepository extends JpaRepository<DocPDF, Long> {

	/**
	 * Busca un documento PDF por su nombre.
	 * 
	 * @param nombre nombre del documento
	 * @return un {@code Optional} que contiene el documento si se encuentra
	 */
	public Optional<DocPDF> findByNombre(String nombre);

	/**
	 * Elimina un documento PDF por su nombre.
	 * 
	 * @param nombre nombre del documento a eliminar
	 */
	public void deleteByNombre(String nombre);
}