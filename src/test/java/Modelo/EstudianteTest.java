package Modelo;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {

    @Test
    public void nuevoEstudiante_SinReservas() {
        Estudiante e = new Estudiante("Harry Potter", "harry@hogwarts.hsh");
        assertEquals(0, e.getReservas().size());
    }

    @Test
    public void agregarReserva_AumentaListaReservas() {
        Estudiante e = new Estudiante("Harry Potter", "harry@hogwarts.hsh");
        Tutor tutor = new Tutor("Severus Snape", "severus@hogwarts.hsh", 100.0, 5);
        Materia materia = new Materia("Matematicas");
        Horario lunes10a12 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));

        e.agregarReserva(new Reserva(e, tutor, materia, lunes10a12));

        assertEquals(1, e.getReservas().size());
    }

    @Test
    public void setters_ActualizacionDatos() {
        Estudiante e = new Estudiante("Harry Potter", "harry@hogwarts.hsh");
        e.setNombre("Harry James Potter");
        e.setCorreo("hjp@hogwarts.hsh");

        assertEquals("Harry James Potter", e.getNombre());
        assertEquals("hjp@hogwarts.hsh", e.getCorreo());
    }
}