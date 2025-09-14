package co.edu.unbosque.sistemaentrenamientoGPC_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
