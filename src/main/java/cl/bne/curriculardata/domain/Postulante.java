// Postulante.java
package cl.bne.curriculardata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;      
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postulantes")
public class Postulante {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;

    @OneToMany(mappedBy = "postulante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore                           // ← rompe la recursión
    @ToString.Exclude                     // ← evita recursión en toString()
    @EqualsAndHashCode.Exclude            // ← evita recursión en equals/hashCode
    private List<ExperienciaLaboral> experiencias = new ArrayList<>();

    public void addExperiencia(ExperienciaLaboral exp) {
        exp.setPostulante(this);
        experiencias.add(exp);
    }
}
