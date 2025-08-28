package cl.bne.curriculardata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postulante {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private List<ExperienciaLaboral> experiencias;
}