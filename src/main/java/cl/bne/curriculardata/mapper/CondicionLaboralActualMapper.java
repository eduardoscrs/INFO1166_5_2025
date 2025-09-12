package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.CondicionLaboralActual;
import cl.bne.curriculardata.dto.DTOCondicionLaboralActual;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CondicionLaboralActualMapper {
    DTOCondicionLaboralActual toDto(CondicionLaboralActual entity);
    CondicionLaboralActual toEntity(DTOCondicionLaboralActual dto);
}
