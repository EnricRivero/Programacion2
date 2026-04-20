package prog2.model;

public abstract class Casa extends Allotjament{
    //ATRIBUTS
    private String mida;
    private int habitacions;
    private int placesPersones;

    //CONSTRUCTOR
    public Casa(String nom, String id, String mida, int habitacions, int placesPersones) {
        super(nom, id, 0, 0);
        this.mida = mida;
        this.habitacions = habitacions;
        this.placesPersones = placesPersones;
    }

    //GETTERS I SETTERS
    public String getMida(){return mida;}
    public void setMetres(String mida){this.mida = mida;}

    public int getHabitacions(){return habitacions;}
    public void setHabitacions(int habitacions){this.habitacions = habitacions;}

    public int getPlacesPersones(){return placesPersones;}
    public void setPlacesPersones(int placesPersones){this.placesPersones = placesPersones;}

    //TOSTRING
    public String toString(){
        return super.toString();
    }
}
