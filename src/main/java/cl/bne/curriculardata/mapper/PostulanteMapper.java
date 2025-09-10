package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.dto.PostulanteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ExperienciaMapper.class)
public interface PostulanteMapper {
    PostulanteDTO toDTO(Postulante postulante);
    Postulante toEntity(PostulanteDTO dto);
}
