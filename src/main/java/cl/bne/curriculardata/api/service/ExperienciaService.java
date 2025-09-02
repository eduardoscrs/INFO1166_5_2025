package cl.bne.curriculardata.api.service; 

import java.util.List; 
import cl.bne.curriculardata.domain.DTOExperiencia.DTOExperienciaLaboral; 

public interface ExperienciaService { 
    List<DTOExperienciaLaboral> list(Long postulanteId); 
    DTOExperienciaLaboral create(Long postulanteId, DTOExperienciaLaboral dto); 
    DTOExperienciaLaboral get(Long postulanteId, Long expId); 
    DTOExperienciaLaboral update(Long postulanteId, Long expId, DTOExperienciaLaboral dto); 
    void delete(Long postulanteId, Long expId); 
}