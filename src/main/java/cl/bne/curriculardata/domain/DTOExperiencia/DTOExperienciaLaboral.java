package cl.bne.curriculardata.domain.DTOExperiencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DTOExperienciaLaboral  {
    private Long id;
    private String empresa;
    private String cargo;
    private int anios;
}