package prog2.model;

import java.time.LocalDate;

public abstract class Allotjament implements InAllotjament{
    //ATRIBUTS
        private String nom;
        private String id;
        private long estadaMinimaALTA;
        private long estadaMinimaBAIXA;

    //CONSTRUCTOR
    public Allotjament(String nom, String id, long estadaMinimaALTA, long estadaMinimaBAIXA){
        this.nom = nom;
        this.id = id;
        this.estadaMinimaALTA = estadaMinimaALTA;
        this.estadaMinimaBAIXA = estadaMinimaBAIXA;
    }

    //GETTERS I SETTERS
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public long getEstadaMinima(Temp temp){
        if (temp == Temp.ALTA) {
            return estadaMinimaALTA;
        } else {
            return estadaMinimaBAIXA;
        }
    }

    public void setEstadaMinima(long estadaMinimaALTA, long estadaMinimaBAIXA){
        this.estadaMinimaALTA = estadaMinimaALTA;
        this.estadaMinimaBAIXA = estadaMinimaBAIXA;
        }

    //CORRECTE FUNCIONAMENT
    public abstract boolean correcteFuncionament();

    //TOSTRING
    public String toString(){
        String c = "Nom=" + nom + ", Id=" + id + ", estada mínima en temp ALTA: " + estadaMinimaALTA + ", estada mínima en temp BAIXA: " + estadaMinimaBAIXA + ".";
        return c;
    }


}