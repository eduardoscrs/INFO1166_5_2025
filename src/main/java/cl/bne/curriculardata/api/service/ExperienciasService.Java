package cl.bne.curriculardata.api.service;

import java.util.List;
import cl.bne.curriculardata.api.dto.*;
import cl.bne.curriculardata.domain.ExperienciaLaboral;

public interface ExperienciaService {
  List<ExperienciaLaboral> list(Long postulanteId);
  ExperienciaLaboral create(Long postulanteId, ExperienciaLaboralCreateDTO dto);
  ExperienciaLaboral get(Long postulanteId, Long expId);
  ExperienciaLaboral update(Long postulanteId, Long expId, ExperienciaLaboralUpdateDTO dto);
  void delete(Long postulanteId, Long expId);
}
