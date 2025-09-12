package cl.bne.curriculardata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postulante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    @OneToMany(mappedBy = "postulante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienciaLaboral> experiencias;
}