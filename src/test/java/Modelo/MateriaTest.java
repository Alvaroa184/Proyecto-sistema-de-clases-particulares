package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MateriaTest {

    @Test
    public void equals_MismoNombreDiferentesMayusculas() {
        assertEquals(new Materia("Matematicas"), new Materia("matematicas"));
    }

    @Test
    public void equals_NombresDistintos() {
        assertNotEquals(new Materia("Matematicas"), new Materia("Historia"));
    }

    @Test
    public void hashCode_MismoNombreDiferentesMayusculas() {
        assertEquals(new Materia("Quimica").hashCode(), new Materia("QUIMICA").hashCode());
    }
}