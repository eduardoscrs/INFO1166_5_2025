package cl.bne.curriculardata.dto;

public class DireccionDTO {
    private String region;
    private String comuna;
    private String calle;
    private String numero;
    private String departamento;
    private String villa;

    // Getters y setters
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getVilla() { return villa; }
    public void setVilla(String villa) { this.villa = villa; }
}
