package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Bitacola implements InBitacola, Serializable {
    private ArrayList<PaginaBitacola> bitacolas;


    /**
     * constructor de la classe Bitacola
     */
    public Bitacola() {
        bitacolas = new ArrayList<>();
    }


    /**
     * Permet afegir una pagina a la llista
     * @param p Objecte de tipus PaginaBitacola
     */
    @Override
    public void afegeixPagina(PaginaBitacola p) {
        bitacolas.add(p);
    }

    /**
     *  Obté una llista amb totes les pàgines d'incidències contingudes dins de la bitàcola
     * @return List<PaginaIncidencies>
     */
    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> inci= new ArrayList<>();
        Iterator<PaginaBitacola> it= bitacolas.iterator();
        PaginaBitacola PBi;

        while (it.hasNext()){
            PBi= it.next();
            if (PBi instanceof PaginaIncidencies) {
                inci.add((PaginaIncidencies)PBi);
            }

        }
    return inci;
    }

    /**
     * Mètode toString per mostrar les dades en el format desitjat
     * @return String
     */
    @Override
    public String toString() {
        String str= "\n#BITACOLA\n";

        int i=0;

        Iterator<PaginaBitacola> it= bitacolas.iterator();
        PaginaBitacola PBi;

        while (it.hasNext()){
            PBi= it.next();
            if (i%3==0){
                str+= "\n\n\n DIA "+ PBi.getDia();
            }

            str+= PBi.toString();



            i++;

        }
    return str;
    }
}
