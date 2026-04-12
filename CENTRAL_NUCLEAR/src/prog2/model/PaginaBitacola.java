package prog2.model;

public class PaginaBitacola extends Bitacola{
    private int dia;

    /**
     *Constructor de PaginaBitacola
     * @param dia
     */
    public PaginaBitacola (int dia){
        this.dia = dia;
    }

    /**
     * getter del dia
     * @return int
     */
    public int getDia() {return dia;}

    /**
     * setter del dia
     * @param dia
     */
    public void setDia(int dia){this.dia = dia;}

    /**
     * Mètode toString per mostrar les dades en el format desitjat
     * @return String
     */
    public String toString(){
        String str = "\n- Dia: " +  dia;
        return str;
    }
}
