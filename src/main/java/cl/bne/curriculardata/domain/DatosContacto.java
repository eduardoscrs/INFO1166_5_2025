package cl.bne.curriculardata.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class DatosContacto {
    private String telefonoCelular;
    private String telefonoAlternativo;
    private String correoElectronico;

    // Getters y setters
    public String getTelefonoCelular() { return telefonoCelular; }
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
    public String getTelefonoAlternativo() { return telefonoAlternativo; }
    public void setTelefonoAlternativo(String telefonoAlternativo) { this.telefonoAlternativo = telefonoAlternativo; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}
