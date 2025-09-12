package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.ResumenPerfil;
import cl.bne.curriculardata.dto.DTOResumenPerfil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResumenPerfilMapper {
    DTOResumenPerfil toDto(ResumenPerfil entity);
    ResumenPerfil toEntity(DTOResumenPerfil dto);
}
