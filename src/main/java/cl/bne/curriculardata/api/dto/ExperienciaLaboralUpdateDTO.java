package cl.bne.curriculardata.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExperienciaLaboralUpdateDTO {
    @NotBlank private String empresa;
    @NotBlank private String cargo;
    private String descripcion;
    @Min(0)  private Integer anios;
}
