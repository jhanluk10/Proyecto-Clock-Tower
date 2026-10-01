
package reto2_JhanCueva;

    public class ObjetoGeografico {
    private String nombre;
    private int idCuerpoDeAgua;
    private String municipio;

    public ObjetoGeografico() {
    }

    public ObjetoGeografico(String nombre, int idCuerpoDeAgua, String municipio) {
        this.nombre = nombre;
        this.idCuerpoDeAgua = idCuerpoDeAgua;
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCuerpoDeAgua() {
        return idCuerpoDeAgua;
    }

    public void setIdCuerpoDeAgua(int idCuerpoDeAgua) {
        this.idCuerpoDeAgua = idCuerpoDeAgua;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}

