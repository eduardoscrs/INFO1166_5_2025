package cl.bne.curriculardata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulanteDTO {
    private Long id;
    private String nombre;     // ← Sin @NotBlank
    private String apellido;   // ← Sin @NotBlank  
    private String email;      // ← Sin @NotBlank y sin @Email
}
