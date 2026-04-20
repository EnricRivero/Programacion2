package prog2.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllotjamentTest {

    private Allotjament allotjament;

    @BeforeEach
    void setUp() {
        // Creem una instància anònima d'Allotjament per a tests

        allotjament = new Allotjament("Allotjament Test", "ID001", 5, 3) {
            @Override
            public boolean correcteFuncionament() {
                return false;
            }
        };

    }

    @Test
    void constructorValid() {
        assertEquals("Allotjament Test", allotjament.getNom());
        assertEquals("ID001", allotjament.getId());
        assertEquals(5, allotjament.getEstadaMinima(InAllotjament.Temp.ALTA));
        assertEquals(3, allotjament.getEstadaMinima(InAllotjament.Temp.BAIXA));
    }



    @Test
    void testSetEstadaMinima() {
        allotjament.setEstadaMinima(6, 4);
        assertEquals(6, allotjament.getEstadaMinima(InAllotjament.Temp.ALTA));
        assertEquals(4, allotjament.getEstadaMinima(InAllotjament.Temp.BAIXA));
    }

    @Test
    void testToString() {
        String expected = "Nom=Allotjament Test, Id=ID001, estada mínima en temp ALTA: 5, estada mínima en temp BAIXA: 3.";
        assertEquals(expected, allotjament.toString());
    }
}



