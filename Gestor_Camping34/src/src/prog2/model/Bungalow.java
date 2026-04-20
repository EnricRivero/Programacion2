package prog2.model;

public class Bungalow extends Casa {
    //ATRIBUTS
    private int placesParquing;
    private boolean terrassa;
    private boolean tv;
    private boolean aireFred;

    //CONSTRUCTOR
    public Bungalow(String nom, String id, String mida, int habitacions, int placesPersones, int placesParquing, boolean terrassa, boolean tv, boolean aireFred) {
        super(nom, id, mida, habitacions, placesPersones);
        this.placesParquing = placesParquing;
        this.terrassa = terrassa;
        this.tv = tv;
        this.aireFred = aireFred;
    }

    //GETTERS I SETTERS
    public int getPlacesParquing(){return placesParquing;}
    public void setPlacesParquing(int placesParquing){this.placesParquing = placesParquing;}

    public boolean isTerrassa(){return terrassa;}
    public void setTerrassa(boolean terrassa){this.terrassa = terrassa;}

    public boolean isTv(){return tv;}
    public void setTv(boolean tv){this.tv = tv;}

    public boolean isAireFred(){return aireFred;}
    public void setAireFred(boolean aireFred){this.aireFred = aireFred;}

    //CORRECTE FUNCIONAMENT
    public boolean correcteFuncionament(){
        return aireFred;
    }

    //TOSTRING
    public String toString(){
        return super.toString();
    }
}
