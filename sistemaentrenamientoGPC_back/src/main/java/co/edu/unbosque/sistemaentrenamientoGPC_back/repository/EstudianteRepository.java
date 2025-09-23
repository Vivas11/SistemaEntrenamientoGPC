package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Estudiante;

/**
 * Repositorio JPA para la entidad {@code Estudiante}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre estudiantes.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
