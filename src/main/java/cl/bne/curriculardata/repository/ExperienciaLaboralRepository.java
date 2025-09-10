package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Long> {
  List<ExperienciaLaboral> findByPostulante_Id(Long postulanteId);
  boolean existsByIdAndPostulante_Id(Long id, Long postulanteId);
  void deleteByIdAndPostulante_Id(Long id, Long postulanteId);
}
