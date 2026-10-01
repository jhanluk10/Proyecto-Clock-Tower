
package reto2_JhanCueva;


    
public class CuerpoDeAgua extends ObjetoGeografico {
    private String tipoCuerpoAgua;
    private String tipoAgua;
    private double irca;

    public CuerpoDeAgua() {
        super();
    }

    public CuerpoDeAgua(String nombre, int idCuerpoDeAgua, String municipio,
                        String tipoCuerpoAgua, String tipoAgua, double irca) {
        super(nombre, idCuerpoDeAgua, municipio);
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
        return getNombre() + " " + getIdCuerpoDeAgua() + " " + getMunicipio() + " "
                + tipoCuerpoAgua + " " + tipoAgua + " " + irca;
    }
}
