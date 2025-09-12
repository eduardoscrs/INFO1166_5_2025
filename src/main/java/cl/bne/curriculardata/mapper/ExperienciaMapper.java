package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExperienciaMapper {

    DTOExperienciaLaboral toDto(ExperienciaLaboral e);

    @Mapping(target = "postulante", ignore = true) // se asigna en el service/controller
    ExperienciaLaboral toEntity(DTOExperienciaLaboral d);
}