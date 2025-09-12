package cl.bne.curriculardata.mapper;

import cl.bne.curriculardata.domain.ReferenciaLaboral;
import cl.bne.curriculardata.dto.DTOReferenciaLaboral;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenciaLaboralMapper {
    DTOReferenciaLaboral toDto(ReferenciaLaboral entity);
    ReferenciaLaboral toEntity(DTOReferenciaLaboral dto);
}
