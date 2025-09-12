package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.domain.DatosContacto;
import cl.bne.curriculardata.domain.Direccion;
import cl.bne.curriculardata.dto.PostulanteDTO;
import cl.bne.curriculardata.dto.DatosContactoDTO;
import cl.bne.curriculardata.dto.DireccionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostulanteMapper {
    PostulanteMapper INSTANCE = Mappers.getMapper(PostulanteMapper.class);

    @Mapping(target = "contacto", source = "contacto")
    @Mapping(target = "direccion", source = "direccion")
    PostulanteDTO toDTO(Postulante postulante);

    @Mapping(target = "contacto", source = "contacto")
    @Mapping(target = "direccion", source = "direccion")
    Postulante toEntity(PostulanteDTO dto);

    DatosContactoDTO datosContactoToDTO(DatosContacto contacto);
    DatosContacto datosContactoToEntity(DatosContactoDTO contactoDTO);

    DireccionDTO direccionToDTO(Direccion direccion);
    Direccion direccionToEntity(DireccionDTO direccionDTO);
}