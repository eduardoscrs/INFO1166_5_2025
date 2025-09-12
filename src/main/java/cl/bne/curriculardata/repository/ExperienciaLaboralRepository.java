package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Long> {
    // Métodos CRUD ya disponibles: findAll(), findById(), save(), deleteById()
    // Agrega métodos personalizados si lo necesitas
}
