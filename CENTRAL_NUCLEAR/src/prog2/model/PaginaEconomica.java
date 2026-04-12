package prog2.model;

public class PaginaEconomica extends PaginaBitacola {
    private float demandaElectrica;
    private float potenciaGenerada;
    private float percentatgeSatisfet;
    private float beneficis;
    private float penalitzacio;
    private float costosOperatius;
    private float guanys;


    /**
     * getter de l'atribut de demanda de potència elèctrica
     * @return float
     */
    public float getDemandaElectrica() {
        return demandaElectrica;
    }

    /**
     * getter de l'atribut de la potència generada
     * @return float
     */
    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    /**
     * getter de l'atribut del percentatge de la demanda de potència satisfeta
     * @return float
     */
    public float getPercentatgeSatisfet() {
        return percentatgeSatisfet;
    }

    /**
     * getter de l'atribut beneficis per potència generada
     * @return float
     */
    public float getBeneficis() {
        return beneficis;
    }

    /**
     * getter de l'atribut penalització per excés de potència
     * @return float
     */
    public float getPenalitzacio() {
        return penalitzacio;
    }

    /**
     * getter per l'atribut de costos operatius
     * @return float
     */
    public float getCostosOperatius() {
        return costosOperatius;
    }

    /**
     * getter de l'atribut de guanys acumulats
     * @return float
     */
    public float getGuanys() {
        return guanys;
    }



    /**
     * setter de l'atribut de demanda de potència elèctrica
     * @param demandaElectrica
     */
    public void setDemandaElectrica(float demandaElectrica) {
        this.demandaElectrica = demandaElectrica;
    }

    /**
     * setter de l'atribut de la potència generada
     * @param potenciaGenerada
     */
    public void setPotenciaGenerada(float potenciaGenerada) {
        this.potenciaGenerada = potenciaGenerada;
    }

    /**
     * setter de l'atribut del percentatge de la demanda de potència satisfeta
     * @param percentatgeSatisfet
     */
    public void setPercentatgeSatisfet(float percentatgeSatisfet) {
        this.percentatgeSatisfet = percentatgeSatisfet;
    }

    /**
     * setter de l'atribut beneficis per potència generada
     * @param beneficis
     */
    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    /**
     * setter de l'atribut penalització per excés de potència
     * @param penalitzacio
     */
    public void setPenalitzacio(float penalitzacio) {
        this.penalitzacio = penalitzacio;
    }

    /**
     * setter per l'atribut de costos operatius
     * @param costosOperatius
     */
    public void setCostosOperatius(float costosOperatius) {
        this.costosOperatius = costosOperatius;
    }

    /**
     * setter de l'atribut de guanys acumulats
     * @param guanys
     */
    public void setGuanys(float guanys) {
        this.guanys = guanys;
    }




    /**
     * Constructor de la classe PaginaEconomica
     * @param dia
     * @param demandaElectrica
     * @param potenciaGenerada
     * @param beneficis
     * @param penalitzacio
     * @param costosOperatius
     * @param guanys
    */
    public PaginaEconomica(int dia,float demandaElectrica,float potenciaGenerada, float beneficis,float penalitzacio,float costosOperatius,float guanys) {
        super(dia);
        this.demandaElectrica = demandaElectrica;
        this.potenciaGenerada = potenciaGenerada;
        this.percentatgeSatisfet = (demandaElectrica > 0) ?potenciaGenerada*100/demandaElectrica: 0;
        this.beneficis = beneficis;
        this.penalitzacio = penalitzacio;
        this.costosOperatius = costosOperatius;
        this.guanys = guanys;
    }




    /**
     * Mètode toString per mostrar les dades en el format desitjat
     * @return String
     */
    @Override
    public String toString() {
        String str=  "\n#Pàgina Economica" +super.toString()+ "\n- Demanda de Potència: " + demandaElectrica+ "\n- Potència Generada: "+ potenciaGenerada+ "\n- Demanda de Potència Satisfeta: "+ percentatgeSatisfet+ "\n- Beneficis: "+ beneficis+ " Unitats Econòmiques"+"\n- Penalització Excés Producció: "+ penalitzacio+ " Unitats Econòmiques"+ "\n- Cost Operatiu: " + costosOperatius +" Unitats Econòmiques"+ "\n- Guanys acumulats: " + guanys + " Unitats Econòmiques\n";
        return str;
    }
}
