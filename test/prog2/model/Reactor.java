package prog2.model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
class ReactorTest {
    private Reactor reactor;

    @BeforeEach
    void setUp(){
        reactor = new Reactor();
    }
    @org.junit.jupiter.api.Test
    void getTemperatura() {
        assertEquals(reactor.getTemperatura(), 25.0f, 0.001f);
    }

    @org.junit.jupiter.api.Test
    void setTemperatura() {
        reactor.setTemperatura(100.0f);
        assertEquals(reactor.getTemperatura(), 100.0f, 0.001f);
    }

    @org.junit.jupiter.api.Test
    void getActivat() {
        assertFalse(reactor.getActivat());
        reactor.activa();
        assertTrue(reactor.getActivat());
    }

    @org.junit.jupiter.api.Test
    void activa() {
        reactor.desactiva();
        reactor.activa();
        assertTrue(reactor.getActivat());
    }

    @org.junit.jupiter.api.Test
    void desactiva() {
        reactor.activa();
        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }

    @org.junit.jupiter.api.Test
    void revisa() {
        PaginaIncidencies pagina = new PaginaIncidencies(1);
        assertDoesNotThrow(() -> reactor.revisa(pagina));
    }

    @org.junit.jupiter.api.Test
    void getCostOperatiu() {
        reactor.activa();
        assertEquals(reactor.getCostOperatiu(), 35.0f, 0.001f);
        reactor.desactiva();
        assertEquals(reactor.getCostOperatiu(), 0.0f, 0.001f);
    }

    @org.junit.jupiter.api.Test
    void calculaOutput() {
        reactor.activa();
        reactor.setTemperatura(100.0f);
        assertEquals(reactor.calculaOutput(100.0f),100.0f, 0.001f);
        reactor.desactiva();
        assertEquals(reactor.calculaOutput(100.0f), 0.0f, 0.001f);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        reactor.setTemperatura(100.0f);
        reactor.activa();
        String result = reactor.toString();
        assertEquals("Temperatura= 100.0, Activat= true", result);
    }
}