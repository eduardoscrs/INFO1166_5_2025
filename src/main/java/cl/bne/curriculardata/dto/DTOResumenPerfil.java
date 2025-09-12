package cl.bne.curriculardata.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DTOResumenPerfil {
    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}