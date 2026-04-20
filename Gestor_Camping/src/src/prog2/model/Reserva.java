

package prog2.model;

import prog2.vista.ExcepcioReserva;

import java.time.LocalDate;

public class Reserva {
    //ATRIBUTS
    private Allotjament allotjament;
    private Client client;
    private LocalDate dataEntrada;
    private LocalDate dataSortida;

    //CONSTRUCTOR
    public Reserva(Allotjament all, Client cli, LocalDate entrada, LocalDate sortida) throws ExcepcioReserva {
        allotjament = all;
        client = cli;
        dataEntrada = entrada;
        dataSortida = sortida;

        if (dataSortida.isBefore(dataEntrada)) {
            throw new ExcepcioReserva("Dates invàlides");
        }
    }

    //GETTERS I SETTERS
    public Allotjament getAllotjament_() {
        return allotjament;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSortida() {
        return dataSortida;
    }



    public void setAllotjament_(Allotjament nouAllotjament) {
        allotjament= nouAllotjament;
    }

    public void setClient(Client nouClient)  {
        client= nouClient;
    }

    public void setDataEntrada(LocalDate novaDataEntrada) {

        dataEntrada=novaDataEntrada;
    }

    public void setDataSortida(LocalDate novaDataSortida) {
        dataSortida= novaDataSortida;
    }

}

