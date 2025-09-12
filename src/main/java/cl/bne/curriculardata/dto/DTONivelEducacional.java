package cl.bne.curriculardata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTONivelEducacional {
    private Long id;

    @NotBlank(message = "El nivel educacional es obligatorio")
    private String nivel;

    @NotBlank(message = "El área es obligatoria")
    private String area;

    @NotBlank(message = "La institución es obligatoria")
    private String institucion;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotNull(message = "El año de egreso es obligatorio")
    private Integer anioEgreso;
}