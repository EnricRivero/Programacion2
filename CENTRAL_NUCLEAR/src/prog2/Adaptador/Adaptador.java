package prog2.adaptador;


import prog2.model.Bitacola;
import prog2.model.BombaRefrigerant;
import prog2.model.Dades;
import prog2.model.Reactor;
import prog2.vista.CentralUBException;

import java.io.*;

public class Adaptador {
    private Dades dades;

    /**
     * Constructor de classe Adaptador
     */
    public Adaptador() {
        dades=new Dades();
    }

    /**
     * getter de la inserció de barres
     * @return float
     */
    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    }

    /**
     * Estableix l'inserció de dades
     * @param in Percentatge d'inserció de les barres de control.
     * @throws CentralUBException
     */
    public void setInsercioBarres(float in) throws CentralUBException {
            dades.setInsercioBarres(in);
    }

    /**
     * Activa el reactor
     * @throws CentralUBException
     */
    public void activaReactor() throws CentralUBException{
            dades.activaReactor();
    }

    /**
     * Desactiva el reactor de la central
     */
    public void desactivaReactor() {
        dades.desactivaReactor();
    }


    /**
     * Retorna un string mostrant les dades del reactor en el format desitjat
     * @return String
     */
    public Reactor mostraEstatReactor() {
        return dades.mostraReactor();
    }

    /**
     * Activa totes les bombes del sistema de refrigeració
     * @throws CentralUBException
     */
    public void activaAllBombes() throws CentralUBException{
        dades.mostraSistemaRefrigeracio().activa();
    }

    /**
     * Desactiva totes les bombes del sistema de refrigeració
     */
    public void desactivaAllBombes() {
        dades.mostraSistemaRefrigeracio().desactiva();
    }

    /**
     * Activa una bomba en concret a través del seu id
     * @param id
     */
    public void activaBomba(int id) {
        dades.activaBomba(id);
    }

    /**
     * Desactiva una bomba en concret a través del seu id
     * @param id
     */
    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }

    /**
     * Retorna un string mostrant les dades del sistema de refrigeracio en el format desitjat
     * @return String
     */
    public String mostraEstatRefrigeracio() {
        return dades.mostraSistemaRefrigeracio().toString();
    }

    /**
     * Retorna un string mostrant les dades de la central en el format desitjat
     * @return String
     */
    public String mostraEstatCentral() {
        return dades.mostraEstat().toString();
    }

    /**
     * Retorna un string mostrant les dades de la Bitacola en el format desitjat
     * @return String
     */
    public String mostraBitacola() {
        return dades.mostraBitacola().toString();
    }

    /**
     * Retorna un string mostrant les dades de les incidencies en el format desitjat
     * @return String
     */
    public String mostraIncidencies() {
        return dades.mostraIncidencies().toString();
    }

    /**
     * Calcula i retorna el percentatge de demanda satisfeta
     * @param demanada
     * @return String
     */
    public String getDemandaSatisfeta(float demanada) {
        float potencia = dades.calculaPotencia();
        return String.valueOf(potencia*100/demanada);

    }

    /**
     * crida al mètode de dades per a finalitzar el dia el qual retorna una bitacola i la passem a String
     * @param demanada
     * @return String
     */
    public String finalitzaDia(float demanada){
        Bitacola b= dades.finalitzaDia(demanada);
        return (b.toString());
    }

    /**
     * Guarda les dades
     * @param camiDesti
     * @throws CentralUBException
     */
    public void guardaDades(String camiDesti) throws CentralUBException{


        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(camiDesti))) {

            oout.writeObject(dades);

        } catch (IOException e) {
            throw new CentralUBException("Error al guardar la centralUB: " + e.getMessage());
        }
    }

    /**
     * Carrega les dades
     * @param camiOrigen
     * @throws CentralUBException
     */
    public void carregaDades(String camiOrigen)throws CentralUBException{

        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades= (Dades) oin.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error al cargar les dades de centralUB: " + e.getMessage());
        }
    }


    /**
     *getter del dia actual
     * @return int
     */
    public int getDia() {
        return dades.getDia();
    }

    /**
     * getter dels guanys acumulats fins al dia actual
     * @return float
     */
    public float getGuanys() {
        return dades.getGuanysAcumulats();

    }

    /**
     * mètode que retorna un objecte bomba
     * @param id
     * @return BombaRefrigerant
     */
    public BombaRefrigerant bomba(int id) {
        return dades.bomba(id);
    }

    /**
     * getter de la demanda de potencia del dia
     * @return float
     */
    public float getDemanda(){
        return dades.getDemanda();
    }
}
