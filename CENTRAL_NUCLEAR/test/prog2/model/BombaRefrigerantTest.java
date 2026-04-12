package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import prog2.vista.CentralUBException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import prog2.vista.CentralUBException;

class BombaRefrigerantTest {
    private BombaRefrigerant bomba;
    private VariableUniforme varUn;

    @BeforeEach
    public void setUp() {
        varUn = new VariableUniforme(1);
        bomba = new BombaRefrigerant(varUn, 1);
    }


    @org.junit.jupiter.api.Test
    void getId() {
        assertEquals(1, bomba.getId());
    }

    @org.junit.jupiter.api.Test
    void getActivatIniciDesactivat() {
        assertFalse(bomba.getActivat());
    }

    @org.junit.jupiter.api.Test
    void getForaDeServeiIniciFalse() {
        assertFalse(bomba.getForaDeServei());
    }

    @org.junit.jupiter.api.Test
    void getVarUn() {
        assertEquals(varUn, bomba.getVarUn());
    }

    @org.junit.jupiter.api.Test
    void setId() {
        bomba.setId(2);
        assertEquals(2, bomba.getId());
    }

    @org.junit.jupiter.api.Test
    void setForaDeServei() {
        bomba.setForaDeServei(true);
        assertTrue(bomba.getForaDeServei());
    }

    @org.junit.jupiter.api.Test
    void setVarUn() {
        VariableUniforme newVarUn = new VariableUniforme(2);
        bomba.setVarUn(newVarUn);
        assertEquals(newVarUn, bomba.getVarUn());
    }

    @org.junit.jupiter.api.Test
    void activaCorrecte() {
        assertDoesNotThrow(() -> bomba.activa());
        assertTrue(bomba.getActivat());
    }
    @org.junit.jupiter.api.Test
    public void activaException() {
        bomba.setForaDeServei(true);
        assertThrows(CentralUBException.class, () -> bomba.activa());
    }

    @org.junit.jupiter.api.Test
    void desactiva() {
        bomba.desactiva();
        assertFalse(bomba.getActivat());
    }

    @org.junit.jupiter.api.Test
    void revisa() {
        PaginaIncidencies pagina = new PaginaIncidencies(1);
        assertDoesNotThrow(() -> bomba.revisa(pagina));
    }

    @org.junit.jupiter.api.Test
    void revisaIncidencia() {
        VariableUniforme varUn = new VariableUniforme(10);
        BombaRefrigerant bomba = new BombaRefrigerant(varUn, 42);
        PaginaIncidencies pagina = new PaginaIncidencies(1);

        bomba.revisa(pagina);
        List<String> incidencies = pagina.getDescripcioIncidencies();

        assertEquals(1, incidencies.size());
        assertTrue(incidencies.get(0).contains("42"));
        assertTrue(incidencies.get(0).contains("fora de servei"));

    }

    @org.junit.jupiter.api.Test
    void getCapacitat() {
        bomba.activa();
        assertEquals(250, bomba.getCapacitat(), 0.001);
    }

    @org.junit.jupiter.api.Test
    void getCostOperatiu() {
        bomba.activa();
        assertEquals(130, bomba.getCostOperatiu(), 0.001);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        String result = bomba.toString();
        assertEquals("Id= 1, Activat= false, Fora de servei= false", result);

    }
}