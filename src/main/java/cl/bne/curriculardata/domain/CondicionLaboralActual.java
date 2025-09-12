package cl.bne.curriculardata.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "condiciones_laborales_actuales")
public class CondicionLaboralActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postulante_id", nullable = false)
    private Postulante postulante;

    private String situacionLaboral;
    private LocalDate fechaInicioSituacion;
    private Integer ultimoSalarioLiquidoMensual;
    private String ultimaActividadLaboral;
    private Boolean buscandoEmpleo;
}