package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import prog2.vista.CentralUBException;

class BitacolaTest {

    private Bitacola bitacola;

    @BeforeEach
    public void setUp() {
        bitacola = new Bitacola();
    }

    @Test
    void afegeixPaginaANDgetIncidencies() {
        PaginaBitacola pagina1 = new PaginaEstat(1, 50, 70, 60, 80, 90);
        PaginaBitacola pagina2 = new PaginaIncidencies(2);
        PaginaBitacola pagina3 = new PaginaIncidencies(3);

        bitacola.afegeixPagina(pagina1);
        bitacola.afegeixPagina(pagina2);
        bitacola.afegeixPagina(pagina3);

        List<PaginaIncidencies> incidencies = bitacola.getIncidencies();

        assertEquals(2, incidencies.size());
        assertTrue(incidencies.get(0).getDia() == 2 || incidencies.get(0).getDia() == 3);
        assertTrue(incidencies.get(1).getDia() == 2 || incidencies.get(1).getDia() == 3);
    }


    @Test
    void testToString() {
        PaginaBitacola pagina1 = new PaginaEstat(1, 50, 70, 60, 80, 90);
        PaginaBitacola pagina2 = new PaginaEconomica(1,10,20,30,40,50,60);
        PaginaBitacola pagina3 = new PaginaIncidencies(1);

        bitacola.afegeixPagina(pagina1);
        bitacola.afegeixPagina(pagina2);
        bitacola.afegeixPagina(pagina3);

        String result = bitacola.toString();
        assertEquals( "\n#BITACOLA\n" + "\n\n\n DIA 1\n" + "#Pàgina Estat\n" +
                "- Dia: 1\n" +
                "- Inserció Barres: 50.0%\n" +
                "- Output Reactor: 70.0 Graus\n" +
                "- Output Sistema de Refrigeració: 60.0 Graus\n" +
                "- Output Generador de Vapor: 80.0 Graus\n" +
                "- Output Turbina: 90.0 Unitats de Potència\n" +
                "\n" +
                "#Pàgina Economica\n" +
                "- Dia: 1\n" +
                "- Demanda de Potència: 10.0\n" +
                "- Potència Generada: 20.0\n" +
                "- Demanda de Potència Satisfeta: 200.0\n" +
                "- Beneficis: 30.0 Unitats Econòmiques\n" +
                "- Penalització Excés Producció: 40.0 Unitats Econòmiques\n" +
                "- Cost Operatiu: 50.0 Unitats Econòmiques\n" +
                "- Guanys acumulats: 60.0 Unitats Econòmiques\n"+
                "\n" +
                "#Pàgina Incidències\n" +
                "- Dia: 1\n", result);
    }
}