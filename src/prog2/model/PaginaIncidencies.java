package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaginaIncidencies extends PaginaBitacola {
    private List<String> incidencies;

    /**
     * getter de la llista amb les descripcions de les incidencies
     * @return List<String>
     */
    public List<String> getDescripcioIncidencies(){
        return incidencies;
    }

    /**
     * setter de la llista amb les descripcions de les incidencies
     * @param incidencies
     */
    public void setDescripcioIncidencies(List<String> incidencies){
        this.incidencies = incidencies;
    }



    /**
     * Constructor classe PaginaIncidencies
     */
    public PaginaIncidencies(int dia) {
        super(dia);
        incidencies= new ArrayList<>();
    }

    /**
     * permet afegir una descripció d'incidencia a la llista
     * @param descIncidencia
     */
    public void afegeixIncidencia(String descIncidencia){
        incidencies.add(descIncidencia);
    }

    /**
     * Mètode toString per mostrar les dades en el format desitjat
     * @return String
     */
    public String toString(){
        String str= "\n#Pàgina Incidències"+super.toString();
                Iterator<String> it= incidencies.iterator();
        while(it.hasNext()){
            str= str + "\n- Descripció Incidència: " + it.next();
        }
        return (str+"\n");
    }
}
