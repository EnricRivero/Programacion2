


package prog2.model;

import prog2.vista.ExcepcioReserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class LlistaReserves implements InLlistaReserves {
    //ATRIBUTS
    private ArrayList<Reserva> reserves;

    //CONSTRUCTOR
    public LlistaReserves() {
        this.reserves = new ArrayList<>();
    }

    //AFEGIR RESERVA
    public void afegirReserva(Allotjament all, Client client, LocalDate entrada, LocalDate sortida) throws ExcepcioReserva {


        if (!(allotjamentDisponible(all,entrada, sortida))) {
            throw new ExcepcioReserva("L’allotjament amb identificador "+ all.getId()+" no està disponible en la data demanada "+ entrada +" pel client "+client.getNom()+ " amb DNI:" + client.getDni());

        } else if (!isEstadaMinima(all, entrada, sortida)) {
            throw new ExcepcioReserva("Les dates sol·licitades pel client " +client.getNom()+ " amb DNI:"+ client.getDni()+" no compleixen l'estada mínima per l'allotjament amb identificador "+ all.getId()+".");
        } else {
            Reserva novaReserva = new Reserva(all, client, entrada, sortida);
            this.reserves.add(novaReserva);
        }

    }
    //GETTERS I SETTERS
    public int getNumReserves(){
        return this.reserves.size();
    }

    //DISPONIBILITAT
    public boolean allotjamentDisponible(Allotjament allot, LocalDate ent, LocalDate sort) {
        Iterator<Reserva> iterator = this.reserves.iterator();

        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            if (reserva.getAllotjament_().equals(allot)) {
                LocalDate entradaExist = reserva.getDataEntrada();
                LocalDate sortidaExist = reserva.getDataSortida();

                if (!(sort.isBefore(entradaExist) || ent.isAfter(sortidaExist))) {
                    return false;
                }
            }
        }

        return true;
    }

    //GETTERS I SETTERS
    public boolean isEstadaMinima(Allotjament allot, LocalDate ent, LocalDate sort) {
        long diesEstada = ChronoUnit.DAYS.between(ent, sort);
        InAllotjament.Temp temporada = Camping.getTemporada(ent);
        return diesEstada >= allot.getEstadaMinima(temporada);
    }

}

