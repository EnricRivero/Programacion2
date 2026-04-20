package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import prog2.vista.CentralUBException;
import prog2.vista.CentralUBException;
class GeneradorVaporTest {
    private GeneradorVapor generador;

    @BeforeEach
    public void setUp() {
        generador = new GeneradorVapor();
    }

    @Test
    void activa() {
        generador.desactiva();
        generador.activa();
        assertTrue(generador.getActivat());
    }

    @Test
    void desactiva() {
        generador.desactiva();
        assertFalse(generador.getActivat());
    }

    @Test
    void getActivat() {
        assertTrue(generador.getActivat());
        generador.desactiva();
        assertFalse(generador.getActivat());
    }



    @Test
    void getCostOperatiu() {
        assertEquals(25, generador.getCostOperatiu(), 0.001);

    }

    @Test
    void calculaOutput() {
        float input = 100;
        assertEquals(90, generador.calculaOutput(input), 0.001);


        generador.desactiva();
        assertEquals(25, generador.calculaOutput(input), 0.001);

    }
}