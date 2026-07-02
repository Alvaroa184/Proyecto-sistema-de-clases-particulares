package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TutorTest {

    private Tutor tutor;
    private Materia matematicas;
    private Horario lunes10a12;

    @BeforeEach
    public void prepararEntorno() {
        tutor = new Tutor("Severus Snape", "severus@hogwarts.hsh", 100.0, 5);
        matematicas = new Materia("Matematicas");
        lunes10a12 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
    }

    @Test
    public void imparteMateria_TutorSiImparteLaMateria() {
        tutor.agregarMateria(matematicas);
        assertTrue(tutor.imparteMateria(matematicas));
    }

    @Test
    public void imparteMateria_TutorNoImparteLaMateria() {
        assertFalse(tutor.imparteMateria(matematicas));
    }

    @Test
    public void Disponible_TutorTieneElHorarioDisponible() {
        tutor.agregarHorario(lunes10a12);
        assertTrue(tutor.Disponible(lunes10a12));
    }

    @Test
    public void Disponible_TutorNoTieneElHorarioDisponible() {
        assertFalse(tutor.Disponible(lunes10a12));
    }

    @Test
    public void tieneCupo_HayCupoDisponible() {
        assertTrue(tutor.tieneCupo(4));
    }

    @Test
    public void tieneCupo_NoHayCupoDisponible() {
        assertFalse(tutor.tieneCupo(5));
    }

    @Test
    public void tieneCupo_SeSuperaElCupoMaximo() {
        assertFalse(tutor.tieneCupo(6));
    }

    @Test
    public void setters_ActualizanLosDatos() {
        tutor.setNombre("Nuevo Nombre");
        tutor.setCorreo("nuevo@hogwarts.hsh");
        tutor.setTarifa(200.0);
        tutor.setMaximoestudiantes(10);

        assertEquals("Nuevo Nombre", tutor.getNombre());
        assertEquals("nuevo@hogwarts.hsh", tutor.getCorreo());
        assertEquals(200.0, tutor.getTarifa());
        assertEquals(10, tutor.getMaximoestudiantes());
    }
}
