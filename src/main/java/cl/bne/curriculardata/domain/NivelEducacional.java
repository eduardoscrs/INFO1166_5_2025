package cl.bne.curriculardata.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "niveles_educacionales")
public class NivelEducacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postulante_id", nullable = false)
    private Postulante postulante;

    private String nivel;
    private String area;
    private String institucion;
    private String titulo;
    private Integer anioEgreso;
}