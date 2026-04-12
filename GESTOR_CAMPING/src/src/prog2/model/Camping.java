
package prog2.model;


import prog2.vista.ExcepcioReserva;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class Camping implements InCamping{
    //ATRIBUTS
    private String nom;
    private ArrayList<Allotjament> llistaAllotjaments;
    private ArrayList<Client> llistaClients;
    private LlistaReserves llistaReserves;

    //CONSTRUCTOR
    public Camping(String nom){
        this.nom = nom;
        this.llistaAllotjaments = new ArrayList<>();
        this.llistaClients = new ArrayList<>();
        this.llistaReserves = new LlistaReserves();
    }

    //GETTERS I SETTERS
   public String getNom() {
       return nom;
   }

    public LlistaReserves getLlistaReserves() {
        return llistaReserves;
    }

    public ArrayList<Allotjament> getLlistaAllotjaments(){
        return llistaAllotjaments;
    }

    public ArrayList<Client> getLlistaClients(){
        return llistaClients;
    }

    public int getNumAllotjaments(){
        return llistaAllotjaments.size();
    }

    public int getNumClients(){
        return llistaClients.size();
    }

    public int getNumReserves(){
        return llistaReserves.getNumReserves();
    }

    //AFEGIR OBJECTES: CLIENT, ALLOTJAMENTS I RESERVES
    public void afegirClient(String nom_, String dni_) throws ExcepcioReserva {
        Client c= new Client(nom_, dni_);
        llistaClients.add(c);
    }


    public void afegirParcela(String nom_, String idAllotjament_, float metres, boolean connexioElectrica){
        Parcela p= new Parcela(nom_, idAllotjament_, metres, connexioElectrica);
        llistaAllotjaments.add(p);
        p.setEstadaMinima(4,2);
    }

    public void afegirBungalow(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,int placesParquing, boolean terrassa, boolean tv, boolean aireFred){
        Bungalow b = new Bungalow(nom_, idAllotjament_, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred);
        llistaAllotjaments.add(b);
        b.setEstadaMinima(7,4);
    }

    public void afegirBungalowPremium(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones, int placesParquing, boolean terrassa, boolean tv, boolean aireFred, boolean serveisExtra, String codiWifi){
        BungalowPremium bp = new BungalowPremium(nom_, idAllotjament_, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred,serveisExtra, codiWifi);
        llistaAllotjaments.add(bp);
        bp.setEstadaMinima(7,4);
    }

    public void afegirGlamping(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones, String material, boolean casaMascota){
        Glamping g= new Glamping(nom_, idAllotjament_, mida, habitacions, placesPersones, material,casaMascota);
        llistaAllotjaments.add(g);
        g.setEstadaMinima(5,3);
    }

    public void afegirMobilHome(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones, boolean terrassaBarbacoa){
        MobilHome mh= new MobilHome( nom_,  idAllotjament_,  mida,  habitacions,  placesPersones,  terrassaBarbacoa);
        llistaAllotjaments.add(mh);
        mh.setEstadaMinima(5,3);
    }


    public void afegirReserva(String id_, String dni_, LocalDate dataEntrada, LocalDate dataSortida) throws ExcepcioReserva{
        Allotjament all= buscarAllotjament(id_);
        Client cli= buscarClient(dni_);
        if (all==null){
            throw new ExcepcioReserva("ID_INEXISTENT");
        }
        if(cli==null){
            throw new ExcepcioReserva("DNI_INEXISTENT");
        }
        llistaReserves.afegirReserva(all, cli, dataEntrada,dataSortida);
    }

    //METODES CÀLCULS
    public float calculMidaTotalParceles(){
        Iterator<Allotjament> iterator = llistaAllotjaments.iterator();
        Allotjament all;
        float metres=0;
        while (iterator.hasNext()) {
            all= iterator.next();
            if(all instanceof Parcela){
                metres+= ((Parcela) all).getMida();
            }
        }
        return metres;
    }


    public int calculAllotjamentsOperatius(){
        Iterator<Allotjament> iterator = llistaAllotjaments.iterator();
        int operatiu=0;
        Allotjament all;
        while (iterator.hasNext()) {
            all= iterator.next();
            if(all.correcteFuncionament()){
                operatiu++;
            }
        }

        return operatiu;
    }

    public Allotjament getAllotjamentEstadaMesCurta(){
        long estada;
        Allotjament minim= llistaAllotjaments.get(0);
        Allotjament all;
        Iterator<Allotjament> iterator = llistaAllotjaments.iterator();
        while (iterator.hasNext()) {
            all= iterator.next();
            estada= all.getEstadaMinima(InAllotjament.Temp.BAIXA);
            if (estada < minim.getEstadaMinima(InAllotjament.Temp.BAIXA)){
                minim= all;
            }
        }
        return minim;
    }

    //BUSCAR: ALLOTJAMENT I CLIENT
    private Allotjament buscarAllotjament (String id){
        Iterator<Allotjament> iterator = llistaAllotjaments.iterator();
        while (iterator.hasNext()) {
            Allotjament allotjament = iterator.next();
            if (allotjament.getId().equals(id)) {
                return allotjament;
            }
        }
        return null;
    }

    private Client buscarClient (String dni){
        Iterator<Client> iterator = llistaClients.iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getDni().equals(dni)) {
                return client;
            }
        }
        return null;
    }

    //TEMPORADES
    public static InAllotjament.Temp getTemporada(LocalDate data){

        int dia = data.getDayOfMonth();
        int mes = data.getMonthValue();

        if ((mes > 3 && mes < 9) || (mes == 3 && dia >= 21) || (mes == 9 && dia <= 20)) {
            return InAllotjament.Temp.ALTA;
        } else {
            return InAllotjament.Temp.BAIXA;
        }
    }


}
