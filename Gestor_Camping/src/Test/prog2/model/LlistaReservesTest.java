
package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import prog2.vista.ExcepcioReserva;

public class LlistaReservesTest {

    private LlistaReserves llistaReserves;
    private Allotjament allotjament;
    private Client client;
    private LocalDate dataEntrada;
    private LocalDate dataSortida;

    @BeforeEach
    public void setUp() {
        // Inicialització dels objectes necessaris abans de cada prova
        llistaReserves = new LlistaReserves();
        allotjament = new Parcela("Parcela A", "P001", 50.0f, true);
        try {
            client = new Client("Client1", "123456789");
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }
        dataEntrada = LocalDate.of(2024, 5, 15);
        dataSortida = LocalDate.of(2024, 5, 20);
    }

    /**
     * Prova que es pot afegir una reserva quan l'allotjament està disponible
     */
    @Test
    public void testAfegirReservaDisponible() {
        try {
            llistaReserves.afegirReserva(allotjament, client, dataEntrada, dataSortida);
        } catch (ExcepcioReserva e) {
            fail("No s'hauria d'haver llançat cap excepció: " + e.getMessage());
        }
        assertEquals(1, llistaReserves.getNumReserves());
    }

    /**
     * Prova que no es pot afegir una reserva quan l'allotjament no està disponible
     */
    @Test
    public void testAfegirReservaNoDisponible() {
        // Afegim la primera reserva
        try {
            llistaReserves.afegirReserva(allotjament, client, dataEntrada, dataSortida);
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }

       // Creem un nou client
        Client client2;
        try {
            client2 = new Client("Client2", "012345678");
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }

        // Intentem afegir una segona reserva per al mateix allotjament amb dates superposades
        LocalDate novaDataEntrada = LocalDate.of(2024, 5, 18);
        LocalDate novaDataSortida = LocalDate.of(2024, 5, 22);

        ExcepcioReserva excepcio = assertThrows(ExcepcioReserva.class, () -> {
            llistaReserves.afegirReserva(allotjament, client2, novaDataEntrada, novaDataSortida);
        });

        assertTrue(excepcio.getMessage().contains("no està disponible"));
    }

    /**
     * Comprova que l'estada mínima es compleixi
     */
    @Test
    public void testEstadaMinima() {
        Allotjament allotjamentAmbEstadaMinima = new Parcela("Parcela A", "P001", 50.0f, true);
        allotjamentAmbEstadaMinima.setEstadaMinima(7, 0);  // Estada mínima de 7 dies en temporada alta i de 0 dies en temporada baixa.

        LocalDate dataEntradaCurta = LocalDate.of(2024, 7, 1);
        LocalDate dataSortidaCurta = LocalDate.of(2024, 7, 5);  // Estada de 4 dies, hauria de fallar

        try {
            llistaReserves.afegirReserva(allotjamentAmbEstadaMinima, client, dataEntradaCurta, dataSortidaCurta);
            fail("L'estada no compleix l'estada mínima.");
        } catch (ExcepcioReserva e) {
            assertTrue(e.getMessage().contains("no compleixen l'estada mínima"));
        }

        // Test de l'estada mínima vàlida
        LocalDate dataSortidaLonga = LocalDate.of(2024, 7, 8);  // Estada de 7 dies, hauria de passar
        try {
            llistaReserves.afegirReserva(allotjamentAmbEstadaMinima, client, dataEntradaCurta, dataSortidaLonga);
        } catch (ExcepcioReserva e) {
            fail("L'estada hauria de ser vàlida: " + e.getMessage());
        }
    }
}

