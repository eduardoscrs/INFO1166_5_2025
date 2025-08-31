package cl.bne.curriculardata.api.dto;

import java.util.List;
import lombok.Data;

@Data
public class PostulanteDTO {
  private Long id;
  private String nombre;
  private String apellido;
  private String email;
  private List<ExperienciaLaboralDTO> experiencias;
}
