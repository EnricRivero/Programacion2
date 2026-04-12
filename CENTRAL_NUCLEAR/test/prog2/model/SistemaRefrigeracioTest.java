package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SistemaRefrigeracioTest {
    private SistemaRefrigeracio sistema;
    private BombaRefrigerant bomba1;
    private BombaRefrigerant bomba2;
    private VariableUniforme varUn;

    @BeforeEach
    void setUp() {
        varUn = new VariableUniforme(1);
        sistema = new SistemaRefrigeracio();
        bomba1 = new BombaRefrigerant(varUn, 0 );
        bomba2 = new BombaRefrigerant(varUn,1);
    }

    @Test
    void afegirBomba() {
        assertDoesNotThrow(() -> {
            sistema.afegirBomba(bomba1);
            sistema.afegirBomba(bomba2);
        });
        assertEquals(2, sistema.getLlistaBombaRefrigerant().size());

        for (int i = 0; i < 3; i++) {
            sistema.afegirBomba(new BombaRefrigerant(varUn, i+2));
        }
        assertThrows(CentralUBException.class, () -> sistema.afegirBomba(new BombaRefrigerant(varUn, 5)));
    }

    @Test
    void activa() throws CentralUBException {
        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);

        sistema.activa();
        assertTrue(sistema.getActivat());
        assertTrue(bomba1.getActivat());
        assertTrue(bomba2.getActivat());
    }

    @Test
    void desactiva() {
        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);

        sistema.desactiva();
        assertFalse(sistema.getActivat());
        assertFalse(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
    }

    @Test
    void getCostOperatiu() throws CentralUBException {
        assertEquals(0, sistema.getCostOperatiu(), 0.001f);

        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);
        sistema.activa();
        assertEquals(260, sistema.getCostOperatiu(), 0.001f);

        sistema.desactiva();
        assertEquals(0, sistema.getCostOperatiu(), 0.001f);
    }

    @Test
    void calculaOutput() throws CentralUBException {
        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);

        assertEquals(100, sistema.calculaOutput(100), 0.001f);

        sistema.activa();
        assertEquals(100, sistema.calculaOutput(100), 0.001f);

        assertEquals(600, sistema.calculaOutput(600), 0.001f);
    }

    @Test
    void revisa() {
        PaginaIncidencies pagina = new PaginaIncidencies(1);
        sistema.afegirBomba(bomba1);

        assertDoesNotThrow(() -> sistema.revisa(pagina));
    }

    @Test
    void testtoString() {
        sistema.afegirBomba(bomba1);
        String result = sistema.toString();
        assertEquals(result,"\nId= 0, Activat= false, Fora de servei= false" );
    }
}