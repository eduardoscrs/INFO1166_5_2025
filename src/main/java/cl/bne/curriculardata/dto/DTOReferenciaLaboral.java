package cl.bne.curriculardata.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DTOReferenciaLaboral {
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "El puesto es obligatorio")
    private String puesto;

    @NotBlank(message = "La empresa es obligatoria")
    private String empresa;

    @NotBlank(message = "El número telefónico es obligatorio")
    private String nroTelefonico;

    @NotBlank(message = "El email es obligatorio")
    private String email;
}