package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.NivelEducacional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelEducacionalRepository extends JpaRepository<NivelEducacional, Long> {
}