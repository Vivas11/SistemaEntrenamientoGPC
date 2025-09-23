package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Problema;

/**
 * Repositorio JPA para la entidad {@code Problema}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre problemas de programación.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface ProblemaRepository extends JpaRepository<Problema, Long> {

}
