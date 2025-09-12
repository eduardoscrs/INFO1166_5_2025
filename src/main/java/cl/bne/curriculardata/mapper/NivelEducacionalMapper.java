package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.NivelEducacional;
import cl.bne.curriculardata.dto.DTONivelEducacional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NivelEducacionalMapper {
    DTONivelEducacional toDto(NivelEducacional entity);
    NivelEducacional toEntity(DTONivelEducacional dto);
}
