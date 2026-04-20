package prog2.model;

public class Glamping extends Casa{
    //ATRIBUTS
    private String material;
    private boolean casaMascota;

    //CONSTRUCTOR
    public Glamping(String nom, String id, String mida, int habitacions, int placesPersones, String material, boolean casaMascota) {
        super(nom, id, mida, habitacions, placesPersones);
        this.material = mida;
        this.casaMascota = casaMascota;
    }

    //GETTERS I SETTERS
    public String getMaterial(){return material;}
    public void setMaterial(String material){ this.material = material;}

    public boolean getCasaMascota(){return casaMascota;}
    public void setCasaMascota(boolean casaMascota){ this.casaMascota = casaMascota;}

    //CORRECTE FUNCIONAMENT
    public boolean correcteFuncionament(){
        return casaMascota;
    }

    //TOSTRING
    public String toString(){
        return super.toString();
    }
}
