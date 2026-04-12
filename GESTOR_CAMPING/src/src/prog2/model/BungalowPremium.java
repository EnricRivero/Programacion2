package prog2.model;

public class BungalowPremium extends Bungalow{
    //ATRIBUTS
    private boolean serveisExtra;
    private String codiWifi;

    //CONSTRUCTOR
    public BungalowPremium(String nom, String id, String mida, int habitacions, int placesPersones, int placesParquing, boolean terrassa, boolean tv, boolean aireFred, boolean serveisExtra, String codiWifi) {
        super(nom, id, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred);
        this.serveisExtra = serveisExtra;
        this.codiWifi = codiWifi;
    }

    //GETTERS I SETTERS
    public boolean isServeisExtra(){return serveisExtra;}
    public void setServeisExtra(boolean serveisExtra){this.serveisExtra = serveisExtra;}

    public String getCodiWifi(){return codiWifi;}
    public void setCodiWifi(String codiWifi){this.codiWifi = codiWifi;}

    //CORRECTE FUNCIONAMENT
    public boolean correcteFuncionament(){return (codiWifi.length()>=8 && codiWifi.length()<=16) && super.correcteFuncionament();}

    //TOSTRING
    public String toString(){
        return super.toString();
    }
}
