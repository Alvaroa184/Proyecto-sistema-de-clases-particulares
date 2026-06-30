package Controlador;
import java.util.ArrayList;
import Modelo.*;

public class Tutor_con {

    private SistemaDeReserva sistema;

    public Tutor_con(SistemaDeReserva sistema) {
        this.sistema = sistema;
    }

    public void agregarTutor(Tutor tutor) {
        sistema.agregarTutor(tutor);
    }

    public Tutor buscarTutor(String nombre) {
        return sistema.buscarTutor(nombre);
    }
    public boolean editarTutor(String nombre, String nuevoCorreo, double nuevaTarifa, int nuevoMaximo) {
        return sistema.editarTutor(nombre, nuevoCorreo, nuevaTarifa, nuevoMaximo);
    }
    public ArrayList<Tutor> buscarPorMateria(Materia materia) {
        return sistema.buscarTutores(new BusquedaTutorPorMateria(materia));
    }

    public ArrayList<Tutor> buscarPorHorario(Horario horario) {
        return sistema.buscarTutores(new BusquedaTutorPorHorario(horario));
    }
    public ArrayList<Tutor> buscarPorMateriaYHorario(Materia materia, Horario horario) {
        return sistema.buscarTutores(new BusquedaTutorPorMateriaYHorario(materia, horario));
    }
    public ArrayList<Tutor> getTutores() {
        return sistema.getTutores();
    }
}