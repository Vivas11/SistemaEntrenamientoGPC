package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Profesor;

/**
 * Repositorio JPA para la entidad {@code Profesor}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre profesores.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}