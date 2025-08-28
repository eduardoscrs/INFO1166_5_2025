package cl.bne.curriculardata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaLaboral {
    private Long id;
    private String empresa;
    private String cargo;
    private String descripcion;
    private int anios;
}