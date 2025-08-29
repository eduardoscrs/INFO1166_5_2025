package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.dto.PostulanteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostulanteMapper {
    PostulanteMapper INSTANCE = Mappers.getMapper(PostulanteMapper.class);

    PostulanteDTO toDTO(Postulante postulante);
    Postulante toEntity(PostulanteDTO dto);
}
