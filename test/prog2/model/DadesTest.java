package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import prog2.vista.CentralUBException;

class DadesTest {
    private Dades dades;
    private final float DEMANDA_TEST = 1000.0f;

    @BeforeEach
    void setUp() {
        dades = new Dades();
    }

    @Test
    void getInsercioBarres() {
        assertEquals(100.0f, dades.getInsercioBarres(), 0.001f);
    }

    @Test
    void setInsercioBarres() throws CentralUBException {
        dades.setInsercioBarres(50.0f);
        assertEquals(50.0f, dades.getInsercioBarres(), 0.001f);

        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(150.0f));
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(-10.0f));
    }

    @Test
    void activaReactor() {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());
    }

    @Test
    void desactivaReactor() {
        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    @Test
    void mostraReactor() {
        Reactor reactor = dades.mostraReactor();
        assertNotNull(reactor);
        assertEquals(25.0f, reactor.getTemperatura(), 0.001f);
    }

    @Test
    void activaBomba() {
        dades.activaBomba(0);
        assertTrue(dades.mostraSistemaRefrigeracio().getLlistaBombaRefrigerant().get(0).getActivat());
    }

    @Test
    void desactivaBomba() {
        dades.activaBomba(0);
        dades.desactivaBomba(0);
        assertFalse(dades.mostraSistemaRefrigeracio().getLlistaBombaRefrigerant().get(0).getActivat());
    }

    @Test
    void mostraSistemaRefrigeracio() {
        SistemaRefrigeracio sr = dades.mostraSistemaRefrigeracio();
        assertNotNull(sr);
        assertEquals(4, sr.getLlistaBombaRefrigerant().size());
    }

    @Test
    void calculaPotencia() {
        assertEquals(0.0f, dades.calculaPotencia(), 0.001f);
        dades.setInsercioBarres(50.0f);
        dades.activaReactor();
        assertEquals(dades.calculaPotencia(), 945.0f);
    }

    @Test
    void getGuanysAcumulats() {
        assertEquals(0.0f, dades.getGuanysAcumulats(), 0.001f);
    }

    @Test
    void mostraEstat() {
        PaginaEstat pagina = dades.mostraEstat();
        assertNotNull(pagina);
        assertEquals(1, pagina.getDia());
    }

    @Test
    void mostraBitacola() {
        Bitacola bitacola = dades.mostraBitacola();
        assertNotNull(bitacola);
    }

    @Test
    void mostraIncidencies() {
        List<PaginaIncidencies> incidencias = dades.mostraIncidencies();
        assertNotNull(incidencias);
    }

    @Test
    void finalitzaDia() {
        dades.setInsercioBarres(50.0f);
        dades.activaReactor();
        dades.activaBomba(0);

        Bitacola bitacola = dades.finalitzaDia(DEMANDA_TEST);
        assertNotNull(bitacola);

        assertEquals(2, dades.mostraEstat().getDia());
    }
}