package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent, Serializable {
    private ArrayList<BombaRefrigerant> LlistaBombaRefrigerant;
    private boolean activat;

    /**
     * Constructor Sistema Refrigeracio
     */
    public SistemaRefrigeracio(){
        LlistaBombaRefrigerant = new ArrayList<>();
        activat = false;
    }

    /**
     * Obté la llista de bombes
     * @return ArrayList<BombaRefrigerant>
     */
    public ArrayList<BombaRefrigerant> getLlistaBombaRefrigerant() {return LlistaBombaRefrigerant;}

    /**
     * Estableix la llista de bombes
     * @param ListaBombaRefrigerant
     */
    public void setLlistaBombaRefrigerant(ArrayList <BombaRefrigerant> ListaBombaRefrigerant){this.LlistaBombaRefrigerant = ListaBombaRefrigerant;}

    /**
     * Afageix un objecte bomba a la llista de bombes del sistema
     * @param boom
     */
    public void afegirBomba(BombaRefrigerant boom){
        if(LlistaBombaRefrigerant.size()<=4) {
            LlistaBombaRefrigerant.add(boom);
        }
        else{throw new CentralUBException("El sistema de refrigeració ja té el màxim de bombes possibles");}
    }

    @Override
    /**
     * Activa totes les bombes del sistema
     */
    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();

        while (it.hasNext()) {
            BombaRefrigerant boom = it.next();
            boom.activa();
        }
        activat = true;
    }

    @Override
    /**
     * Desactiva totes les bombes del sistema
     */
    public void desactiva() {
        Iterator<BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();
        while (it.hasNext()){
            BombaRefrigerant boom = it.next();
            boom.desactiva();
        }
        activat = false;
    }

    @Override
    /**
     * Obté si totes les bombes del sistema estan activades o desactivades
     */
    public boolean getActivat() {
        return activat;
    }

    @Override
    /**
     * Revisa les bombes del sistema
     */
    public void revisa(PaginaIncidencies p){
        Iterator <BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();
        while (it.hasNext()) {
            BombaRefrigerant boom = it.next();
            boom.revisa(p);
        }
    }

    @Override
    /**
     * Obté el cost operatiu del sistema de refrigeració segons el número de bombes activades
     */
    public float getCostOperatiu() {
        float costOperatiu = 0;
        Iterator<BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();
        while(it.hasNext()){
            BombaRefrigerant boom = it.next();
            costOperatiu += boom.getCostOperatiu();
        }
        return costOperatiu;
    }

    @Override
    /**
     * Calcula l'output del sistema de refrigeració
     */
    public float calculaOutput(float input) {
        float output = input;
        Iterator<BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();
        while (it.hasNext()) {
            BombaRefrigerant boom = it.next();
            if (boom.getActivat()) {
                output += 250;
            }
        }
        return (input < output) ? input : output;
    }

    /**
     * To string del sistema
     * @return string
     */
    public String toString(){
        if(LlistaBombaRefrigerant.isEmpty()){throw new CentralUBException("El sistema de refrigeració no te bombes");}
        String str = "";
        Iterator<BombaRefrigerant> it = LlistaBombaRefrigerant.iterator();
        while (it.hasNext()) {
            BombaRefrigerant boom = it.next();
            str += "\n" + boom.toString();
            }
        return str;
    }
}
