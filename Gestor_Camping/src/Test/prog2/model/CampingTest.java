package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.ExcepcioReserva;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CampingTest {

    private Camping camping;
    private Client client;
    private Parcela parcela;
    private Bungalow bungalow;
    private LocalDate dataEntrada;
    private LocalDate dataSortida;

    @BeforeEach
    public void setUp() {
        // Inicialitzem un Camping buit
        camping = new Camping("Camping La Playa");

        // Creem un client
        try {
            client = new Client("Laura", "123456789");
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }
        // Afegim una reserva
        try {
            camping.afegirClient(client.getNom(), client.getDni()); // Añadimos al cliente al camping
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }

        // Creem 2 allotjaments i els afegim al camping
        parcela = new Parcela("Parcela 1", "P001", 100, true);  // Suponiendo que Parcela tiene este constructor
        bungalow = new Bungalow("Bungalow 1", "B001", "Mitjà", 2, 4, 1, true, true, true);
        camping.afegirParcela(parcela.getNom(), parcela.getId(), parcela.getMida(), parcela.isConnexioElectrica());
        camping.afegirBungalow(bungalow.getNom(), bungalow.getId(), bungalow.getMida(), bungalow.getHabitacions(), bungalow.getPlacesPersones(), bungalow.getPlacesParquing(), bungalow.isTerrassa(), bungalow.isTv(), bungalow.isAireFred());

        // Dates de reserva
        dataEntrada = LocalDate.of(2024, 5, 1);
        dataSortida = LocalDate.of(2024, 5, 10);
    }

    @Test
    void testAfegirClient() throws ExcepcioReserva {
        assertEquals(1, camping.getNumClients());
    }

    @Test
    void testAfegirAllotjaments() {
        assertEquals(2, camping.getNumAllotjaments());
    }

    /**
     * Prova que si s'intenta fer una reserva amb un allotjament no existent, es llanci l'excepció ExcepcioReserva.
     */
    @Test
    public void testAfegirReservaAllotjamentNoExistent() {
        // Intentem fer una reserva amb un id d'allotjament que no existeix
        assertThrows(ExcepcioReserva.class, () -> {
            camping.afegirReserva("ID_INEXISTENT", client.getDni(), dataEntrada, dataSortida);
        });
    }

    /**
     * Comprovem que si el client no existeix, es llanci l'excepció ExcepcioReserva.
     */
    @Test
    public void testAfegirReservaClientNoExistent() {
        // Intentem fer una reserva amb un DNI de client que no existeix
        assertThrows(ExcepcioReserva.class, () -> {
            camping.afegirReserva(parcela.getId(), "DNI_INEXISTENT", dataEntrada, dataSortida);
        });
    }

    /**
     * Prova que una reserva es pugui afegir correctament quan les dades d'allotjament i client són vàlides.
     * @throws ExcepcioReserva
     */
    @Test
    public void testAfegirReservaCorrecta()  {
        // Afegim una reserva correctament
        try {
            camping.afegirReserva(parcela.getId(), client.getDni(), dataEntrada, dataSortida);
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }

        // Verifiquem que la reserva ha estat afegida correctament
        assertEquals(1, camping.getNumReserves());
    }

    /**
     * Verifica que si intentem fer una reserva sobre un allotjament ja ocupat en les mateixes dates, es llanci una excepció.
     * @throws ExcepcioReserva
     */
    @Test
    public void testAfegirReservaIncorrecta() throws ExcepcioReserva {
        // Intentem reservar un allotjament per a una data ja reservada
        camping.afegirReserva(parcela.getId(), client.getDni(), dataEntrada, dataSortida);

        // Intentem reservar el mateix allotjament amb una data ja ocupada
        assertThrows(ExcepcioReserva.class, () -> {
            camping.afegirReserva(parcela.getId(), client.getDni(), dataEntrada, dataSortida);
        });
    }

    /**
     * Comprova que el càlcul de la mida total de les parcel·les es realitzi correctament.
     */
    @Test
    public void testCalculMidaTotalParceles() {
        // Comprovem el càlcul de la mida total de les parcel·les
        float midaTotal = camping.calculMidaTotalParceles();
        assertEquals(100, midaTotal, 0.01);  // 100 és la mida de la parcel·la que hem afegit
    }

    /**
     * Verifica que es calculi correctament el nombre d'allotjaments operatius.
     */
    @Test
    public void testCalculAllotjamentsOperatius() {
        // Afegim un bungalow y una parcel·la
        int allotjamentsOperatius = camping.calculAllotjamentsOperatius();

        // Comproven que el número d'allotjaments operatius sigui 2
        assertEquals(2, allotjamentsOperatius);
    }

    /**
     * Assegura que el nom del càmping es torna correctament.
     */
    @Test
    public void testGetNom() {
        // Comprovem el nom del camping
        assertEquals("Camping La Playa", camping.getNom());
    }

    /**
     * Verifica que un nou tipus d'allotjament, en aquest cas un BungalowPremium, s'afegeix correctament al càmping.
     */
    @Test
    public void testAfegirBungalowPremium() {
        // Afegim un Bungalow Prèmium i comprovem que s'ha afegit correctament
        BungalowPremium bungalowPremium = new BungalowPremium("Bungalow Premium", "BP001", "Gran", 2, 4, 2, true, true, true, true, "WiFi123");
        camping.afegirBungalowPremium(bungalowPremium.getNom(), bungalowPremium.getId(), bungalowPremium.getMida(), bungalowPremium.getHabitacions(), bungalowPremium.getPlacesPersones(), bungalowPremium.getPlacesParquing(), bungalowPremium.isTerrassa(), bungalowPremium.isTv(), bungalowPremium.isAireFred(), bungalowPremium.isServeisExtra(), bungalowPremium.getCodiWifi());

        // Verifiquem que s'hagi afegit
        assertEquals(3, camping.getNumAllotjaments());  // Ara hauria d'haver 3 allotjaments
    }

    /**
     * Comprova que la temporada de 4 dates diferents es torna correctament.
     */
    @Test
    void testGetTemporada() {
        assertEquals(InAllotjament.Temp.ALTA, Camping.getTemporada(LocalDate.of(2024, 6, 1)));
        assertEquals(InAllotjament.Temp.BAIXA, Camping.getTemporada(LocalDate.of(2024, 12, 1)));
        assertEquals(InAllotjament.Temp.ALTA, Camping.getTemporada(LocalDate.of(2024, 3, 21)));
        assertEquals(InAllotjament.Temp.BAIXA, Camping.getTemporada(LocalDate.of(2024, 3, 20)));
    }


}





/*

Explicación del archivo JUnit:
setUp():

Este método se ejecuta antes de cada prueba y se encarga de inicializar un Camping, agregar un Client, y crear distintos tipos de alojamientos (por ejemplo, Parcela, Bungalow). También define las fechas de entrada y salida para las reservas.
Pruebas de reserva:


testAfegirReservaClientNoExistente(): Similar, pero comprobamos que si el cliente no existe, también se lance la excepción.
testAfegirReservaCorrecta(): Prueba que una reserva se pueda añadir correctamente cuando los datos de alojamiento y cliente son válidos.
testAfegirReservaIncorrecta(): Verifica que si intentamos hacer una reserva sobre un alojamiento ya ocupado en las mismas fechas, se lance una excepción.
Pruebas de funcionalidades adicionales:

testCalculMidaTotalParceles(): Comprueba que el cálculo de la medida total de las parcelas se realice correctamente.
testCalculAllotjamentsOperatius(): Verifica que se calcule correctamente el número de alojamientos operativos.
testGetNom(): Asegura que el nombre del camping se devuelve correctamente.
testAfegirBungalowPremium(): Verifica que un nuevo tipo de alojamiento, en este caso un BungalowPremium, se añada correctamente al camping.
Consideraciones adicionales:
En los métodos de test relacionados con la reserva, se está utilizando la clase ExcepcioReserva para verificar que se manejen correctamente las excepciones.
Los métodos de prueba utilizan assertThrows() para garantizar que se lanzan las excepciones adecuadas cuando se cometen errores (por ejemplo, al intentar reservar un alojamiento no existente).
El código asume que las clases Parcela, Bungalow, BungalowPremium y otros tipos de alojamiento tienen constructores adecuados y que implementan correctamente sus métodos.
Con este JUnit, puedes verificar las funcionalidades clave de la clase Camping y asegurar que se comporta correctamente en varios escenarios.
 */