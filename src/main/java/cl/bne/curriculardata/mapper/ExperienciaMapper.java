package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;

import org.springframework.stereotype.Component;

@Component
public class ExperienciaMapper {
  public DTOExperienciaLaboral toDto(ExperienciaLaboral e) {
    if (e == null) return null;
    return new DTOExperienciaLaboral(e.getId(), e.getEmpresa(), e.getCargo(), e.getAnios());
  }
  public ExperienciaLaboral toEntity(DTOExperienciaLaboral d) {
    if (d == null) return null;
    return new ExperienciaLaboral(d.getId(), null, d.getEmpresa(), d.getCargo(), null, d.getAnios());
  }
}
