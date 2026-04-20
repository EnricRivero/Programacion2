package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Turbina implements InComponent, Serializable {
    private boolean activat;

    private final static float cost = 20;

    /**
     * Constructor de turbina
     */
    public Turbina() {
        activat=true;
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
     * Mètode que activa la turbina
     * @throws CentralUBException si cal
     */
    @Override
    public void activa() throws CentralUBException {
        activat = true;

    }

    /**
     *  Mètode que desactiva la turbina
     */
    @Override
    public void desactiva() {
        activat = false;
    }



    /**
     * Mètode que revisa la turbina, com no té cap incidencia no fa res
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
        return activat? cost: 0;
    }

    /**
     * calcula l'output del component turbina
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        if (activat && (input >=100)) {
            return (2*input);
        }
        else{
            return 0;
        }
    }
}
