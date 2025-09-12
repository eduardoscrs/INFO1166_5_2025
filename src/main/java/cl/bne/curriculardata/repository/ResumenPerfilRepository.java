package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.ResumenPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumenPerfilRepository extends JpaRepository<ResumenPerfil, Long> {
}