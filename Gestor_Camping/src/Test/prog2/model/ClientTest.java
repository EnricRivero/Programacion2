package prog2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import prog2.vista.ExcepcioReserva;


public class ClientTest {

    /**
     *  Prova que el DNI correcte (9 caràcters) no llanci excepció
     */
    @Test
    public void testDniCorrecte() {
        Client client = null;
        try {
            client = new Client("Laura", "123456789");
        } catch (ExcepcioReserva e) {
            throw new RuntimeException(e);
        }
        assertEquals("123456789", client.getDni());
    }

    /**
     * Prova que un DNI llarg (més de 9 caràcters) llanci excepció
     */
    @Test
    public void testDniIncorrecteLLarg() {
        ExcepcioReserva excepcio = assertThrows(ExcepcioReserva.class, () -> {
            new Client("Laura", "1234567890");
        });
        assertTrue(excepcio.getMessage().contains("El DNI ha de tenir 9 caràcters."));
    }

    /**
     * Prova que un DNI curt (menys de 9 caràcters) llanci excepció
     */
    @Test
    public void testDniIncorrecteCurt() {
        ExcepcioReserva excepcio = assertThrows(ExcepcioReserva.class, () -> {
            new Client("Laura", "12345");
        });
        assertTrue(excepcio.getMessage().contains("El DNI ha de tenir 9 caràcters."));
    }
}
