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

    @Test
    public void buscarTutor_NombreDistintoComoRegistro() {
        assertSame(tutor, sistema.buscarTutor("severus snape"));
    }

    @Test
    public void buscarTutor_NoExiste() {
        assertNull(sistema.buscarTutor("Albus Dumbledore"));
    }

    @Test
    public void buscarEstudiante_CorreoDistintoComoRegistro() {
        assertSame(estudiante, sistema.buscarEstudiante("HARRY@hogwarts.hsh"));
    }

    @Test
    public void buscarEstudiante_NoExiste() {
        assertNull(sistema.buscarEstudiante("ron@hogwarts.hsh"));
    }

    @Test
    public void buscarTutores_EstrategiaPorMateria_Exitoso() {
        Tutor otro = new Tutor("Pomona Sprout", "pomona@hogwarts.hsh", 80.0, 3);
        otro.agregarMateria(new Materia("Herbologia"));
        sistema.agregarTutor(otro);

        ArrayList<Tutor> resultado = sistema.buscarTutores(new BusquedaTutorPorMateria(matematicas));

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(tutor));
    }

    @Test
    public void editarTutor_TutorExiste_Exitoso() {
        boolean editado = sistema.editarTutor("Severus Snape", "nuevo@hogwarts.hsh", 150.0, 8);

        assertTrue(editado);
        assertEquals("nuevo@hogwarts.hsh", tutor.getCorreo());
        assertEquals(150.0, tutor.getTarifa());
        assertEquals(8, tutor.getMaximoestudiantes());
    }

    @Test
    public void editarTutor_TutorNoExiste_Detectado() {
        assertFalse(sistema.editarTutor("Pomona Sprout", "nuevo@hogwarts.hsh", 1.0, 1));
    }

    @Test
    public void editarEstudiante_EstudianteExiste_Existoso() {
        boolean editado = sistema.editarEstudiante("harry@hogwarts.hsh", "Harry James Potter", "hjp@hogwarts.hsh");

        assertTrue(editado);
        assertEquals("Harry James Potter", estudiante.getNombre());
        assertEquals("hjp@hogwarts.hsh", estudiante.getCorreo());
    }

    @Test
    public void editarEstudiante_EstudianteNoExiste_Detectado() {
        assertFalse(sistema.editarEstudiante("ron@hogwarts.hsh", "Ronald Bilius Weasley", "nuevo@hogwarts.hsh"));
    }
}