package cl.bne.curriculardata.domain;

import java.util.List;

public class Postulante {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private List<ExperienciaLaboral> experiencias;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<ExperienciaLaboral> getExperiencias() { return experiencias; }
    public void setExperiencias(List<ExperienciaLaboral> experiencias) { this.experiencias = experiencias; }
}
package cl.bne.curriculardata.domain;

import java.util.List;

public class Postulante {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private List<ExperienciaLaboral> experiencias;
    // getters y setters
}
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
