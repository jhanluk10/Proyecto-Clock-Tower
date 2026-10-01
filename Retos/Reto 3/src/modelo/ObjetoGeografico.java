package modelo;

/**
 * Superclase que representa un objeto geográfico.
 */
public class ObjetoGeografico {

    private String nombre;
    private int id;
    private String municipio;

    public ObjetoGeografico() {
    }

    public ObjetoGeografico(String nombre, int id, String municipio) {
        this.nombre = nombre;
        this.id = id;
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
