package cl.bne.curriculardata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;  
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postulantes")
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;

    private String tipoDocumento;      // RUT, etc.
    private String numeroDocumento;    // 11111111-1
    private String primerApellido;
    private String segundoApellido;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String estadoCivil;

    @Embedded
    private DatosContacto contacto;

    @Embedded
    private Direccion direccion;

    @OneToMany(mappedBy = "postulante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore                           // evita recursión JSON
    @ToString.Exclude                     // evita recursión en toString()
    @EqualsAndHashCode.Exclude            // evita recursión en equals/hashCode
    private List<ExperienciaLaboral> experiencias = new ArrayList<>();

    // Métodos de ayuda
    public void addExperiencia(ExperienciaLaboral exp) {
        exp.setPostulante(this);
        experiencias.add(exp);
    }
}
