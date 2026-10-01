package modelo;

/**
 * Subclase que representa un cuerpo de agua.
 * Extiende de ObjetoGeografico e implementa el método nivel().
 */
public class CuerpoDeAgua extends ObjetoGeografico {

    private String tipoCuerpoAgua;
    private String tipoAgua;
    private double irca;

    public CuerpoDeAgua() {
        super();
    }

    public CuerpoDeAgua(String nombre, int id, String municipio,
                        String tipoCuerpoAgua, String tipoAgua, double irca) {
        super(nombre, id, municipio);
        this.tipoCuerpoAgua = tipoCuerpoAgua;
        this.tipoAgua = tipoAgua;
        this.irca = irca;
    }

    public String getTipoCuerpoAgua() {
        return tipoCuerpoAgua;
    }

    public void setTipoCuerpoAgua(String tipoCuerpoAgua) {
        this.tipoCuerpoAgua = tipoCuerpoAgua;
    }

    public String getTipoAgua() {
        return tipoAgua;
    }

    public void setTipoAgua(String tipoAgua) {
        this.tipoAgua = tipoAgua;
    }

    public double getIrca() {
        return irca;
    }

    public void setIrca(double irca) {
        this.irca = irca;
    }

    /**
     * Calcula el nivel de riesgo según el valor IRCA de la instancia.
     * Rangos según la tabla del reto:
     * (80 - 100] → INVIABLE SANITARIAMENTE
     * (35 - 80]  → ALTO
     * (14 - 35]  → MEDIO
     * (5 - 14]   → BAJO
     * [0 - 5]    → SIN RIESGO
     */
    public String nivel() {
        if (irca > 80 && irca <= 100) {
            return "INVIABLE SANITARIAMENTE";
        } else if (irca > 35 && irca <= 80) {
            return "ALTO";
        } else if (irca > 14 && irca <= 35) {
            return "MEDIO";
        } else if (irca > 5 && irca <= 14) {
            return "BAJO";
        } else if (irca >= 0 && irca <= 5) {
            return "SIN RIESGO";
        } else {
            return "VALOR IRCA INVALIDO";
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Municipio: %s | Tipo Cuerpo: %s | Tipo Agua: %s | IRCA: %.2f | Nivel: %s",
                getId(), getNombre(), getMunicipio(), tipoCuerpoAgua, tipoAgua, irca, nivel());
    }
}
