/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;

    /**
     * Generador aleatori de la demanda de potència
     **/
    private VariableNormal variableNormal;

    /**
     * Demanda de potència del dia actual
     **/
    private float demandaPotencia;
    private Adaptador adaptador;
    private Scanner sc = new Scanner(System.in);
    private Scanner subopcio = new Scanner(System.in);
    private Scanner aux = new Scanner(System.in);


    enum OpcionsMenu {opcio_1, opcio_2, opcio_3, opcio_4, opcio_5, opcio_6, opcio_7, opcio_8, opcio_9, opcio_10, opcio_11}
    enum MenuBarres {B1, B2, B3}
    enum MenuReactor {R1, R2, R3, R4}
    enum MenuRefrigeracio {REF1, REF2, REF3, REF4, REF5, REF6}

    String str;

    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();

    }


    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        String[] descMenu = {"Gestió Barres de Control", "Gestió Reactor", "Gestió Sistema Refrigeració", "Mostrar Estat Central", "Mostrar Bitàcola", "Mostrar Incidències", "Obtenir Demanda Satisfeta amb Configuració Actual", "Finalitzar Dia", "Guardar Dades", "Carrega Dades", "Sortir"};
        String[] descBarres = {"Obtenir Inserció Barres", "Establir Inserció Barres", "Sortir"};
        String[] descReactor = {"Activar Reactor", "Desactivar Reactor", "Mostrar Estat", "Sortir"};
        String[] descRef = {"Activar Totes les Bombes", "Desactivar Totes les Bombes", "Activar Bomba", "Desactivar Bomba", "Mostra Estat", "Sortir"};

        Menu<OpcionsMenu> menu = new Menu<>("Menú", OpcionsMenu.values());
        menu.setDescripcions(descMenu);
        OpcionsMenu opcio = null;

        Menu<MenuBarres> menuB = new Menu<>("Menú Barres de Control", MenuBarres.values());
        menuB.setDescripcions(descBarres);
        MenuBarres opcioB = null;

        Menu<MenuReactor> menuR = new Menu<>("Menú Barres de Control", MenuReactor.values());
        menuR.setDescripcions(descReactor);
        MenuReactor opcioR = null;

        Menu<MenuRefrigeracio> menuRef = new Menu<>("Menú Barres de Control", MenuRefrigeracio.values());
        menuRef.setDescripcions(descRef);
        MenuRefrigeracio opcioRef = null;


        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio) {

                case opcio_1:
                    System.out.println("Has triat l'opció 1, gestió de barres de control");
                    do {
                        menuB.mostrarMenu();
                        opcioB = menuB.getOpcio(subopcio);

                        switch (opcioB) {
                            case B1:
                                System.out.println("Has triat l'opció 1, obtenir inserció de barres");
                                str = String.valueOf(adaptador.getInsercioBarres());
                                System.out.println("Inserció de barres: "+str);
                                break;

                            case B2:
                                System.out.println("Has triat l'opció 2, establir inserció de barres");
                                System.out.println("Digues el valor per establir com a grau d'inserció de les barres");
                                float num= aux.nextFloat();
                                adaptador.setInsercioBarres(num);
                                System.out.println(str);
                                break;

                            case B3:
                                System.out.println("Has triat l'opció 3, sortir");
                                break;

                        }

                    } while (opcioB != MenuBarres.B3);
                    break;


                case opcio_2:
                    System.out.println("Has triat l'opció 2, gestió de reactor");
                    do {
                        menuR.mostrarMenu();
                        opcioR = menuR.getOpcio(subopcio);

                        switch (opcioR) {
                            case R1:
                                System.out.println("Has triat l'opció 1, activar Reactor");
                                adaptador.activaReactor();
                                break;

                            case R2:
                                System.out.println("Has triat l'opció 2, desactivar Reactor");
                                adaptador.desactivaReactor();
                                break;

                            case R3:
                                System.out.println("Has triat l'opció 3, mostrar estat del Reactor");
                                str = adaptador.mostraEstatReactor().toString();
                                System.out.println(str);
                                break;

                            case R4:
                                System.out.println("Has triat l'opció 4, sortir");
                                break;

                        }

                    } while (opcioR != MenuReactor.R4);
                    break;


                case opcio_3:
                    System.out.println("Has triat l'opció 3, gestió del sistema de refrigeració");
                    do {
                        menuRef.mostrarMenu();
                        opcioRef = menuRef.getOpcio(subopcio);

                        switch (opcioRef) {
                            case REF1:
                                System.out.println("Has triat l'opció 1, activar totes les bombes");
                                adaptador.activaAllBombes();
                                break;

                            case REF2:
                                System.out.println("Has triat l'opció 2, desactivar totes les bombes");
                                adaptador.desactivaAllBombes();
                                System.out.println("Totes les bombes han estat desactivades.");
                                break;

                            case REF3:
                                System.out.println("Has triat l'opció 3, activar una bomba");
                                System.out.print("Introdueix l'id de la bomba (0-3): ");
                                int idBA = aux.nextInt();
                                adaptador.activaBomba(idBA);
                                break;


                            case REF4:
                                System.out.println("Has triat l'opció 4, desactivar una bomba");
                                System.out.print("Introdueix l'id de la bomba (0-3): ");
                                int idBD = aux.nextInt();
                                adaptador.desactivaBomba(idBD);
                                System.out.println("Bomba "+ idBD+ " desactivada.");
                                break;


                            case REF5:
                                System.out.println("Has triat l'opció 5, mostrar estat del sistema de refrigeració");
                                str = adaptador.mostraEstatRefrigeracio();
                                System.out.println(str);
                                break;

                            case REF6:
                                System.out.println("Has triat l'opció 6, sortir");
                                break;

                        }

                    } while (opcioRef != MenuRefrigeracio.REF6);
                    break;

                case opcio_4:
                    System.out.println("Has triat l'opció 4, mostrar l'estat de la central");
                    str = adaptador.mostraEstatCentral();
                    System.out.println(str);
                    break;

                case opcio_5:
                    System.out.println("Has triat l'opció 5, mostrar bitàcola");
                    str = adaptador.mostraBitacola().toString();
                    System.out.println(str);
                    break;

                case opcio_6:
                    System.out.println("Has triat l'opció 6, mostrar les incidències");
                    str = adaptador.mostraIncidencies();
                    System.out.println(str);
                    break;

                case opcio_7:
                    System.out.println("Has triat l'opció 7, Obtenir Demanda Satisfeta amb Configuració Actual");
                    str = adaptador.getDemandaSatisfeta(demandaPotencia);
                    System.out.println(str +"%");
                    break;

                case opcio_8:
                    System.out.println("Has triat l'opció 8, finalitzar dia");
                    finalitzaDia();
                    break;

                case opcio_9:
                    System.out.println("Has triat l'opció 9, guardar dades");
                    adaptador.guardaDades("dades.txt");
                    System.out.println("Dades guardades");
                    break;

                case opcio_10:
                    System.out.println("Has triat l'opció 10, carrega dades");
                    adaptador.carregaDades("dades.txt");
                    System.out.println("Dades carregades");
                    break;


                case opcio_11:
                    System.out.println("Has triat l'opció 11, sortir\n Fins la propera.");
                    break;


            }


        } while (opcio != OpcionsMenu.opcio_11);

    }


    private float generaDemandaPotencia() {
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }

    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");

        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }

}

