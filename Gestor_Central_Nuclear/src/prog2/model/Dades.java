/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades, Serializable {
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;


    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    public float guanysAcumulats;

    private float demanda;

    /**
     * Constructor de la classe dades, per defecte fa:
     * Inserció barres 100
     * Crea un reactor desactivat
     * Crea el sistema de refrigeració i li afegeix 4 bombes en estat no actiu
     * Crea el generador de vapor en estat actiu
     * Crea la turbina en estat actiu
     * Crea la bitacola
     */
    public  Dades(){
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;

        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);



        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }

    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou
     * beneficis, penalització per excès de potència, costos operatius y
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float potenciaGenerada = calculaPotencia();
        float beneficis = Math.min(potenciaGenerada, demandaPotencia);
        float penalitzacio = (potenciaGenerada > demandaPotencia) ? PENALITZACIO_EXCES_POTENCIA : 0f;

        float costos = reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() + generadorVapor.getCostOperatiu() + turbina.getCostOperatiu();
        guanysAcumulats += (beneficis - penalitzacio - costos);

        return new PaginaEconomica(dia, demandaPotencia, potenciaGenerada, beneficis, penalitzacio, costos, guanysAcumulats);
    }

    /**
     * Aquest mètode ha d'establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputRefri = sistemaRefrigeracio.calculaOutput(outputReactor);
        float temperatura = outputReactor - (outputReactor-outputRefri);
        if(temperatura < 25){
            temperatura = 25;
        }
        reactor.setTemperatura(temperatura);
    }


    /**
     * getter de la inserció de barres
     * @return float
     */
    @Override
    public float getInsercioBarres() {return insercioBarres;}

    /**
     * Estableix l'inserció de dades
     * @param insercioBarres Percentatge d'inserció de les barres de control.
     * @throws CentralUBException
     */
    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if(insercioBarres < 0|| insercioBarres > 100){
            throw new CentralUBException("No es permet fixar un grau d’inserció fora de l’interval (0-100)");
        }
        this.insercioBarres = insercioBarres;
    }

    /**
     * Activa el reactor
     * @throws CentralUBException
     */
    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    /**
     * Desactiva el reactor de la central
     */
    @Override
    public void desactivaReactor() {
        reactor.desactiva();
    }

    /**
     * Retorna l'objecte que contè el reactor de la central.
     */
    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void activaBomba(int id) throws CentralUBException {
        ArrayList<BombaRefrigerant> llistaBombes = sistemaRefrigeracio.getLlistaBombaRefrigerant();
        Iterator<BombaRefrigerant> it = llistaBombes.iterator();
        BombaRefrigerant boom = llistaBombes.get(0);
        while(boom.getId() != id) {
            boom = it.next();
        }
        boom.activa();
    }

    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void desactivaBomba(int id) {
        ArrayList<BombaRefrigerant> llistaBombes = sistemaRefrigeracio.getLlistaBombaRefrigerant();
        Iterator<BombaRefrigerant> it = llistaBombes.iterator();
        BombaRefrigerant boom = llistaBombes.get(0);
        while(boom.getId() != id) {
            boom = it.next();
        }
        boom.desactiva();
    }

    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     */
    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    /**
     * Retorna la potència generada per la central. Aquesta potència es
     * l'output de la turbina. Es pot consultar la Figura 2 a l'enunciat per
     * veure els detalls.
     */
    @Override
    public float calculaPotencia() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputRefri = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGener = generadorVapor.calculaOutput(outputRefri);
        float outputTurbina = turbina.calculaOutput(outputGener);
        return outputTurbina;
    }

    /**
     * Retorna els guanys acumulats actuals.
     */
    @Override
    public float getGuanysAcumulats() {return guanysAcumulats;}

    /**
     * Retorna una pàgina de estat per a la configuració actual de la central.
     */
    @Override
    public PaginaEstat mostraEstat() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputRefri = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGener = generadorVapor.calculaOutput(outputRefri);
        float outputTurbina = turbina.calculaOutput(outputGener);

        return new PaginaEstat(dia, insercioBarres, outputReactor,outputRefri, outputGener, outputTurbina);
    }

    /**
     * Retorna la bitacola de la central.
     */
    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de
     * la central.
     */
    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();

    }


    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
    }

    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia.
     * Per això és necessari coneixer la demanda de potència actual per al dia
     * en curs.
     * @param demandaPotencia Demanda de potència actual de la central.
     */
    public Bitacola finalitzaDia(float demandaPotencia) {
        demanda=demandaPotencia;
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);

        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);

        // Incrementa dia
        dia += 1;

        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);

        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }



    /**
     *getter del dia actual
     * @return int
     */
    public int getDia(){
        return dia;
    }

    /**
     * mètode que retorna un objecte bomba
     * @param id
     * @return BombaRefrigerant
     */
    public BombaRefrigerant bomba(int id){
        ArrayList<BombaRefrigerant> bombes= sistemaRefrigeracio.getLlistaBombaRefrigerant();

        Iterator<BombaRefrigerant> it = bombes.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            if(bomba.getId() == id){
                return bomba;
            }
        }
      return null;
    }


    /**
     * getter de la demanda de potencia del dia
     * @return float
     */
    public float getDemanda() {
        return demanda;
    }
}
