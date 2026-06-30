package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaDeReservaTest {

    private SistemaDeReserva sistema;
    private Tutor tutor;
    private Estudiante estudiante;
    private Materia matematicas;
    private Horario lunes10a12;

    @BeforeEach
    public void prepararEntorno() {
        sistema = new SistemaDeReserva();

        matematicas = new Materia("Matematicas");
        lunes10a12 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));

        tutor = new Tutor("Severus Snape", "severus@hogwarts.hsh", 100.0, 5);
        tutor.agregarMateria(matematicas);
        tutor.agregarHorario(lunes10a12);

        estudiante = new Estudiante("Harry Potter", "harry@hogwarts.hsh");

        sistema.agregarTutor(tutor);
        sistema.agregarEstudiante(estudiante);
    }

    @Test
    public void agregarTutorExitoso() {
        assertEquals(1, sistema.getTutores().size());
        assertTrue(sistema.getTutores().contains(tutor));
    }

    @Test
    public void agregarEstudiante_Exitoso() {
        assertEquals(1, sistema.getEstudiantes().size());
        assertTrue(sistema.getEstudiantes().contains(estudiante));
    }

    @Test
    public void crearReserva_DatosValidos() {
        boolean creada = sistema.crearReserva(estudiante, tutor, matematicas, lunes10a12);

        assertTrue(creada);
        assertEquals(1, sistema.getReservas().size());
        assertEquals(1, estudiante.getReservas().size());
    }

    @Test
    public void crearReserva_TutorIncorrecto() {
        Materia historia = new Materia("Historia");
        boolean creada = sistema.crearReserva(estudiante, tutor, historia, lunes10a12);

        assertFalse(creada);
        assertEquals(0, sistema.getReservas().size());
    }

    @Test
    public void crearReserva_HorarioTutorIncorrecto() {
        Horario martes10a12 = new Horario(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        boolean creada = sistema.crearReserva(estudiante, tutor, matematicas, martes10a12);

        assertFalse(creada);
        assertEquals(0, sistema.getReservas().size());
    }

    @Test
    public void crearReserva_ConflictoHorarioTutor() {
        Horario lunes11a13 = new Horario(DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(13, 0));
        tutor.agregarHorario(lunes11a13);
        Estudiante otro = new Estudiante("Ron Weasley", "ron@hogwarts.hsh");
        sistema.agregarEstudiante(otro);

        assertTrue(sistema.crearReserva(estudiante, tutor, matematicas, lunes10a12));
        assertFalse(sistema.crearReserva(otro, tutor, matematicas, lunes11a13));
        assertEquals(1, sistema.getReservas().size());
    }

    @Test
    public void crearReserva_TutorDiferenteHorario_Exitoso() {
        Horario lunes14a16 = new Horario(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0));
        tutor.agregarHorario(lunes14a16);

        assertTrue(sistema.crearReserva(estudiante, tutor, matematicas, lunes10a12));
        assertTrue(sistema.crearReserva(estudiante, tutor, matematicas, lunes14a16));
        assertEquals(2, sistema.getReservas().size());
    }

}