package cl.bne.curriculardata.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostulanteUpdateDTO {
  @NotBlank private String nombre;
  @NotBlank private String apellido;
  @Email @NotBlank private String email;
}
