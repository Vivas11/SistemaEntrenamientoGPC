package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

/**
 * Repositorio JPA para la entidad {@code Usuario}.
 * Proporciona operaciones CRUD y consultas personalizadas sobre usuarios.
 * Extiende {@link JpaRepository} para heredar métodos como {@code findAll()}, {@code save()}, {@code deleteById()}, etc.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
