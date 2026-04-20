package prog2.model;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {
    private int id;
    private boolean activat;
    private boolean fora_servei;
    private VariableUniforme varUn;

    private final static float capacitat = 250;
    private final static float cost = 130;

    /**
     * Constructor de la classe BombaRefrigerant
     * @param id
     * @param varUn
     */
    public BombaRefrigerant(VariableUniforme varUn, int id) {
        this.id = id;
        this.activat = false;
        this.fora_servei = false;
        this.varUn=varUn;

    }


    /**
     * getter de identificador de la bomba
     * @return int
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * getter de l'atribut activat que determina l'estat de la bomba
     * @return boolean
     */
    @Override
    public boolean getActivat() {
        return activat;
    }

    /**
     * getter de l'atribut fora_servei que determina si la bomba està o no fora de servei
     * @return boolean
     */
    public boolean getForaDeServei() {
        return fora_servei;
    }

    /**
     * getter de variable uniforme
     * @return
     */
    public VariableUniforme getVarUn() {
        return varUn;
    }

    /**
     * Setter de identificador
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * setter del fora de servei
     * @param fora_servei
     */
    public void setForaDeServei(boolean fora_servei) {
        this.fora_servei = fora_servei;
    }

    /**
     * setter de variable uniforme
     * @param varUn
     */
    public void setVarUn(VariableUniforme varUn) {
        this.varUn = varUn;
    }



    /**
     * Mètode que activa la bomba
     * @throws CentralUBException si està fora de servei
     */
    @Override
    public void activa() throws CentralUBException {
        if (fora_servei) {
            throw new CentralUBException("La bomba "+ ((int)id+1)+ " està fora de servei");
        }
        activat = true;
    }

    /**
     *  Mètode que desactiva la bomba
     */
    @Override
    public void desactiva() {
       activat = false;
    }


    /**
     * Mètode que determina si una bomba quedará fora de servei amb 25% de probabilitat el dia següent
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba
     * es queda fora de servei.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        int valor= varUn.seguentValor();
        if (valor <= 25){
            fora_servei = true;
            activat = false;
            p.afegeixIncidencia("La bomba refrigerant " + ((int)id+1) + "  està fora de servei");
        }
        else{
            fora_servei = false;
        }

    }


    /**
     * Obté capacitat de refrigeració en graus
     * @return float
     */
    @Override
    public float getCapacitat() {
        return activat ? capacitat : 0;
    }

    /**
     * getter del cost Operatiu de la bomba
     * @return float
     */
    @Override
    public float getCostOperatiu() {
        return activat ? cost : 0;
    }


    /**
     * toString per mostrar les dades amb el format desitjat
     * @return String
     */
    public String toString(){
        String str= "Id= " +id+ ", Activat= "+ activat + ", Fora de servei= "+ fora_servei;
        return str;
    }
}
