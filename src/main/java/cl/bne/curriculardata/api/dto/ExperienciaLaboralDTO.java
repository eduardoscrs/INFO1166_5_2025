package cl.bne.curriculardata.api.dto;

import lombok.Data;

@Data
public class ExperienciaLaboralDTO {
    private Long id;
    private Long postulanteId;
    private String empresa;
    private String cargo;
    private String descripcion;
    private Integer anios;
}
