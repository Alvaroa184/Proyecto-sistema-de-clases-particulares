package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class HorarioTest {

    private Horario lunes10a12;
    private Horario lunes11a13;
    private Horario martes10a12;
    private Horario lunes12a14;

    @BeforeEach
    public void prepararEntorno() {
        lunes10a12 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        lunes11a13 = new Horario(DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(13, 0));
        martes10a12 = new Horario(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        lunes12a14 = new Horario(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(14, 0));
    }

    @Test
    public void Superpone_MismoDiaSuperponiendose() {
        assertTrue(lunes10a12.Superpone(lunes11a13));
    }

    @Test
    public void superpone_DiasDistintos() {
        assertFalse(lunes10a12.Superpone(martes10a12));
    }

    @Test
    public void superpone_MismoDiaSinSuperponerse() {
        assertFalse(lunes10a12.Superpone(lunes12a14));
    }

    @Test
    public void equals_HorariosIdenticos() {
        Horario lunes1012 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        assertEquals(lunes10a12, lunes1012);
    }

    @Test
    public void equals_HorariosDistintos() {
        Horario lunes14a16 = new Horario(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));
        assertNotEquals(lunes10a12, lunes14a16);
    }

    @Test
    public void hashCode_HorariosIguales() {
        Horario lunes1012 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        assertEquals(lunes10a12.hashCode(), lunes1012.hashCode());
    }
}
