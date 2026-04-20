package prog2.model;

public class PaginaEstat extends PaginaBitacola{
    private float grauBarresControl;
    private float bcReactor;
    private float bcSistemaRefrigeracio;
    private float bcGeneradorVapor;
    private float bcTurbina;

    /**
     * Constructor de la PaginaEstat
     * @param dia
     * @param grauBarresControl
     * @param bcReactor
     * @param bcSistemaRefrigeracio
     * @param bcGeneradorVapor
     * @param bcTurbina
     */
    public PaginaEstat(int dia, float grauBarresControl, float bcReactor, float bcSistemaRefrigeracio, float  bcGeneradorVapor, float bcTurbina) {
        super(dia);
        this.grauBarresControl = grauBarresControl;
        this.bcReactor = bcReactor;
        this.bcSistemaRefrigeracio = bcSistemaRefrigeracio;
        this.bcGeneradorVapor = bcGeneradorVapor;
        this.bcTurbina = bcTurbina;
    }

    /**
     * getter del grau d'inserció de les barres de control
     * @return float
     */
    public float getGrauBarresControl() {return grauBarresControl;}

    /**
     * setter del grau d'inserció de les barres de control
     * @param grauBarresControl
     */
    public void setGrauBarresControl(float grauBarresControl){this.grauBarresControl = grauBarresControl;}

    /**
     * getter del output de reactor
     * @return float
     */
    public float getBcReactor() {return bcReactor;}

    /**
     * setter del output de reactor
     * @param bcReactor
     */
    public void setBcReactor(float bcReactor){this.bcReactor = bcReactor;}

    /**
     * getter del output de Sistema de refrigeració
     * @return float
     */
    public float getBcSistemaRefrigeracio() {return bcSistemaRefrigeracio;}

    /**
     * setter del output de Sistema de refrigeració
     * @param bcSistemaRefrigeracio
     */
    public void setBcSistemaRefrigeracio(float bcSistemaRefrigeracio){this.bcSistemaRefrigeracio = bcSistemaRefrigeracio;}

    /**
     * getter del output de generador de vapor
     * @return float
     */
    public float getBcGeneradorVapor() {return bcGeneradorVapor;}

    /**
     * setter del output de generador de vapor
     * @param bcGeneradorVapor
     */
    public void setBcGeneradorVapor(float bcGeneradorVapor){this.bcGeneradorVapor = bcGeneradorVapor;}

    /**
     * getter del output de turbina
     * @return float
     */
    public float getBcTurbina() {return bcTurbina;}

    /**
     * setter del output de turbina
     * @param bcTurbina
     */
    public void setBcTurbina(float bcTurbina){this.bcTurbina = bcTurbina;}


    /**
     * Mètode toString per mostrar les dades en el format desitjat
     * @return String
     */
    @Override
    public String toString() {
        String str =  "\n#Pàgina Estat"+super.toString()
                + "\n- Inserció Barres: " + grauBarresControl + "%"
                + "\n- Output Reactor: " + bcReactor + " Graus"
                + "\n- Output Sistema de Refrigeració: " + bcSistemaRefrigeracio + " Graus"
                + "\n- Output Generador de Vapor: " + bcGeneradorVapor + " Graus"
                + "\n- Output Turbina: " + bcTurbina + " Unitats de Potència\n";
        return str;
    }
}
