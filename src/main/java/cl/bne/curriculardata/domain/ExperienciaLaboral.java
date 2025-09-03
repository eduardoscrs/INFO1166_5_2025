package cl.bne.curriculardata.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa la experiencia laboral de un postulante")
public class ExperienciaLaboral {
    
    @Schema(description = "ID único de la experiencia laboral", example = "1")
    private Long id;
    
    @Schema(description = "Nombre de la empresa", example = "Banco de Chile")
    private String empresa;
    
    @Schema(description = "Cargo ocupado en la empresa", example = "Desarrollador Senior")
    private String cargo;
    
    @Schema(description = "Descripción de las responsabilidades y logros", example = "Desarrollo de aplicaciones web con Spring Boot")
    private String descripcion;
    
    @Schema(description = "Años de experiencia en el cargo", example = "3")
    private int anios;
}