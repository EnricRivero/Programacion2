package prog2.model;

public class MobilHome extends Casa{
    //ATRIBUTOS
    private boolean terrassaBarbacoa;

    //CONSTRUCTOR
    public MobilHome(String nom, String id, String mida, int habitacions, int placesPersones, boolean terrassaBarbacoa) {
        super(nom, id, mida, habitacions, placesPersones);
        this.terrassaBarbacoa = terrassaBarbacoa;
    }

    //GETTERS I SETTERS
    public boolean getTerrassaBarbacoa(){return terrassaBarbacoa;}
    public void setTerrassaBarbacoa(boolean terrassaBarbacoa) {this.terrassaBarbacoa = terrassaBarbacoa;}

    //CORRECTE FUNCIONAMENT
    public boolean correcteFuncionament(){
        return terrassaBarbacoa;
    }

    //TOSTRING
    public String toString(){
        return super.toString();
    }
}
