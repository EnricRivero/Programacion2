package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class GeneradorVapor implements InComponent, Serializable {
    private boolean activat;
    private final float eficiencia= 0.9f;
    private final float temperatura=25;

    private final static float cost = 25;

    /**
     * Constructor de generador de vapor
     */
    public GeneradorVapor() {
        activat=true;
    }

    /**
     * Mètode que activa la el generador de vapor
     * @throws CentralUBException si cal
     */
    @Override
    public void activa() throws CentralUBException {

        activat = true;

    }

    /**
     *  Mètode que desactiva el generador de vapor
     */
    @Override
    public void desactiva() {
        activat = false;
    }


    /**
     * getter de l'atribut activat que determina l'estat de la turbina
     * @return boolean
     */
    @Override
    public boolean getActivat() {
        return activat;
    }



    /**
     * Mètode que revisa el generador de vapor, com no té cap incidencia no fa res
     * @param p Objecte de tipus PaginaIncidencies
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        //no fa res
    }


    /**
     * getter del cost Operatiu de la turbina
     * @return float
     */
    @Override
    public float getCostOperatiu() {
        return cost;
    }

    /**
     * calcula l'output del component generador de vapor
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        if (activat) {
            return input*eficiencia;
        }else{
            return temperatura;
        }
    }
}
