
package prog2.model;

import prog2.vista.ExcepcioReserva;

public class Client {
    //ATRIBUTS
    private String DNI;
    private String nom;

    //CONSTRUCTOR
    public Client(String nom, String dni) throws ExcepcioReserva {
        this.nom = nom;
        DNI = setDNI(dni);
    }

    //GETTERS I SETTERS
    public String setDNI(String dni) throws ExcepcioReserva {
        if (dni.length() != 9) {
            throw new ExcepcioReserva("El DNI ha de tenir 9 caràcters.");
        } else {
            DNI = dni;
            return DNI;
        }
    }

    public String getDni() {
        return DNI;
    }

    public String getNom(){
    return nom;
    }

    //TOSTRING
    public String toString(){
        String c= "\nClient ("+ "Nom: " + nom + ", DNI: " + DNI + ")";
        return c;
    }
}