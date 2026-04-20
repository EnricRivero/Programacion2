package prog2.model;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Reactor implements InComponent, Serializable {
    private float temperatura;
    private boolean activat;

    public Reactor() {
        this.temperatura = 25;
        this.activat = false;
    }

    /**
     * Obté la temperatura
     * @return float
     */
    public float getTemperatura(){return temperatura;}

    /**
     * Estableix la temperatura
     * @param temperatura
     */
    public void setTemperatura(float temperatura){this.temperatura = temperatura;}

    /**
     * Retorna si el component està activat o no.
     */
    public boolean getActivat(){return activat;}

    /**
     * Activa el component. El mètode llançarà
     * una excepció en determinades situacions explicades
     * a la Taula 1 de l'enunciat de la pràctica.
     */
    public void activa() throws CentralUBException{
        if(temperatura > 1000){throw new CentralUBException("No es pot activar el reactor mentre es superi la temperatura màxima de 1.000 graus.");}
        activat = true;
    }

    /**
     * Desactiva el component.
     */
    public void desactiva(){this.activat = false;}

    /**
     * Revisa el component. Com a resultat de la revisió, podria sorgir
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    public void revisa (PaginaIncidencies p){
        if(temperatura>1000){
            p.afegeixIncidencia("El reactor tenia una temperatura superior als 1000 graus");
            desactiva();
        }
    }

    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de
     * l'enunciat de la pràctica.
     */
    public float getCostOperatiu(){;
        if(activat){ return 35;}
        else{ return 0;}
    }

    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     */
    public float calculaOutput(float input){
        if(activat){ return (temperatura + (100-input) * 10);}
        else{ return 0;}
    }

    /**
     * String per mostrar les dades
     * @return String
     */
    public String toString(){
        String str= "Temperatura= " +temperatura+ ", Activat= "+ activat;
        return str;
    }
}
