package cl.bne.curriculardata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postulante_id")
    private Postulante postulante;

    private String empresa;
    private String cargo;
    private String descripcion;
    private Integer anios;
}