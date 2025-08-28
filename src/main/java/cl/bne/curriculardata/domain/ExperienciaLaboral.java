package cl.bne.curriculardata.domain;

public class ExperienciaLaboral {
    private Long id;
    private String empresa;
    private String cargo;
    private int anios;
    private String descripcion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public int getAnios() { return anios; }
    public void setAnios(int anios) { this.anios = anios; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
package cl.bne.curriculardata.domain;

public class ExperienciaLaboral {
    private Long id;
    private String empresa;
    private String cargo;
    private int anios;
    private String descripcion;
    // getters y setters
}
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
