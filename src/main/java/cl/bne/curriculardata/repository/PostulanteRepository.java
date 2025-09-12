package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulanteRepository extends JpaRepository<Postulante, Long> {
  // Métodos CRUD ya disponibles: findAll(), findById(), save(), deleteById()
  // Agrega métodos personalizados si lo necesitas
}
