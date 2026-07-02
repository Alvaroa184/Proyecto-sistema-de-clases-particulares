package Modelo;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    public void getters_ConfiguracionExitosa() {
        Estudiante estudiante = new Estudiante("Harry Potter", "harry@hogwarts.hsh");
        Tutor tutor = new Tutor("Severus Snape", "severus@hogwarts.hsh", 100.0, 5);
        Materia materia = new Materia("Matematicas");
        Horario lunes10a12 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));

        Reserva reserva = new Reserva(estudiante, tutor, materia, lunes10a12);

        assertSame(estudiante, reserva.getEstudiante());
        assertSame(tutor, reserva.getTutor());
        assertSame(materia, reserva.getMateria());
        assertSame(lunes10a12, reserva.getHorario());
    }
}