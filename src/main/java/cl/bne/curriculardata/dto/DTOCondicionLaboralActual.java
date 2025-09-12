package cl.bne.curriculardata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class DTOCondicionLaboralActual {
    private Long id;

    @NotBlank(message = "La situación laboral es obligatoria")
    private String situacionLaboral;

    @NotNull(message = "La fecha de inicio de la situación es obligatoria")
    private LocalDate fechaInicioSituacion;

    @NotNull(message = "El salario líquido mensual es obligatorio")
    private Integer ultimoSalarioLiquidoMensual;

    @NotBlank(message = "La última actividad laboral es obligatoria")
    private String ultimaActividadLaboral;

    @NotNull(message = "Debe indicar si está buscando empleo")
    private Boolean buscandoEmpleo;
}