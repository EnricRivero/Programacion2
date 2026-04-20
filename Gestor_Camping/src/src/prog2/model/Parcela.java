package prog2.model;
public class Parcela extends Allotjament{
    //ATRIBUTOS
    private float metres;
    private boolean connexioElectrica;

    //CONSTRUCTOR
    public Parcela(String nom, String id, float metres, boolean connexioElectrica) {
        super(nom, id, 4, 2);
        this.metres = metres;
        this.connexioElectrica = connexioElectrica;
    }

    //GETTERS I SETTERS
    public float getMida(){return metres;}
    public void setMida(float metres){this.metres = metres;}

    public boolean isConnexioElectrica(){return connexioElectrica;}
    public void setConnexioElectrica(boolean connexioElectrica){this.connexioElectrica = connexioElectrica;}

    //CORRECTE FUNCIONAMENT
    public boolean correcteFuncionament(){return connexioElectrica;}

    //TOSTRING
    public String toString(){
        return super.toString();
    }

}