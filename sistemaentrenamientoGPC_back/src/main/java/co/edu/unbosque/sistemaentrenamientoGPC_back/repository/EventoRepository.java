package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Evento;

/**
 * Repositorio JPA para la entidad {@code Evento}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre eventos.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
