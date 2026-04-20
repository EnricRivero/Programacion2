package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class TurbinaTest {
    private Turbina turbina;

    @BeforeEach
    public void setUp() {
        turbina = new Turbina();
    }

    @Test
    void getActivat() {
        assertTrue(turbina.getActivat());
    }

    @Test
    void activa() {
        turbina.desactiva();
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    @Test
    void desactiva() {
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    @Test
    void getCostOperatiu() {
        assertEquals(20, turbina.getCostOperatiu(), 0.001);

    }

    @Test
    public void getCostOperatiuDesactivada() {
        turbina.desactiva();
        assertEquals(0, turbina.getCostOperatiu(), 0.001);
    }

    @Test
    void calculaOutput() {
        assertEquals(200, turbina.calculaOutput(100), 0.001);
    }

    @Test
    public void calculaOutputDesactivada() {
        turbina.desactiva();
        assertEquals(0, turbina.calculaOutput(100), 0.001);
    }


}
