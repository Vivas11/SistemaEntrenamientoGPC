package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Tema;

/**
 * Repositorio JPA para la entidad {@code Tema}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre temas.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface TemaRepository extends JpaRepository<Tema, Long> {

}
