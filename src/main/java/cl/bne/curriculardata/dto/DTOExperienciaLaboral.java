package cl.bne.curriculardata.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOExperienciaLaboral  {
    private Long id;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String empresa;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private int anios;
}
