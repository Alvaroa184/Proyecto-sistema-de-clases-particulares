package Modelo;

import java.util.ArrayList;

public class SistemaDeReserva {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Tutor> tutores = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public SistemaDeReserva() {
        this.reservas = new ArrayList<>();
        this.tutores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }
    public boolean agregarTutor(Tutor tutor) {
        if (buscarTutor(tutor.getNombre()) != null) {
            return false;
        }
        tutores.add(tutor);
        return true;
    }
    public boolean agregarEstudiante(Estudiante estudiante) {
        if (buscarEstudiante(estudiante.getCorreo()) != null) {
            return false;
        }
        estudiantes.add(estudiante);
        return true;
    }
    public boolean crearReserva(Estudiante estudiante, Tutor tutor, Materia materia, Horario horario) {
        if (!tutor.imparteMateria(materia)) {
            return false;
        }
        if (!tutor.Disponible(horario)) {
            return false;
        }
        if (ExisteChoqueDeHorario(tutor, horario)) {
            return false;
        }
        if (!tutor.tieneCupo(contarReservasTutor(tutor, horario))) {
            return false;
        }
        Reserva reserva = new Reserva(estudiante, tutor, materia, horario);
        reservas.add(reserva);
        estudiante.agregarReserva(reserva);
        return true;
    }
    private int contarReservasTutor(Tutor tutor, Horario horario) {
        int contador = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getTutor().equals(tutor) && reserva.getHorario().equals(horario)) {
                contador++;
            }
        }
        return contador;
    }
    private boolean ExisteChoqueDeHorario(Tutor tutor,Horario horario) {
        for(Reserva reserva : reservas){
            if(reserva.getTutor().equals(tutor) && reserva.getHorario().Superpone(horario)){
                return true;
            }
        }
        return false;
    }
    public void eliminarTutor(Tutor tutor) {
        reservas.removeIf(reserva -> reserva.getTutor().equals(tutor));
        tutores.remove(tutor);
    }
    public void eliminarEstudiante(Estudiante estudiante) {
        reservas.removeIf(reserva -> reserva.getEstudiante().equals(estudiante));
        estudiantes.remove(estudiante);
    }
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public boolean cancelarReserva(Estudiante estudiante, Tutor tutor, Horario horario) {
        Reserva reservaEncontrada = null;
        for (Reserva reserva : reservas) {
            if (reserva.getEstudiante().equals(estudiante) && reserva.getTutor().equals(tutor) && reserva.getHorario().equals(horario)) {
                reservaEncontrada = reserva;
                break;
            }
        }
        if (reservaEncontrada != null) {
            reservas.remove(reservaEncontrada);
            estudiante.getReservas().remove(reservaEncontrada);
            return true;
        }
        return false;
    }

    public ArrayList<Tutor> buscarTutores(EstrategiaBusquedaTutor estrategia) {
        return estrategia.buscar(tutores);
    }

    public Tutor buscarTutor(String nombre) {
        for (Tutor tutor : tutores) {
            if (tutor.getNombre().equalsIgnoreCase(nombre)) {
                return tutor;
            }
        }
        return null;
    }

    public Estudiante buscarEstudiante(String correo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCorreo().equalsIgnoreCase(correo)) {
                return estudiante;
            }
        }
        return null;
    }

    public boolean editarTutor(String nombre, String nuevoCorreo, double nuevaTarifa, int nuevoMaximo) {
        Tutor tutor = buscarTutor(nombre);
        if (tutor == null) {
            return false;
        }
        tutor.setCorreo(nuevoCorreo);
        tutor.setTarifa(nuevaTarifa);
        tutor.setMaximoestudiantes(nuevoMaximo);
        return true;
    }
    public boolean editarEstudiante(String correo, String nuevoNombre, String nuevoCorreo) {
        Estudiante estudiante = buscarEstudiante(correo);
        if (estudiante == null) {
            return false;
        }
        estudiante.setNombre(nuevoNombre);
        estudiante.setCorreo(nuevoCorreo);
        return true;
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public ArrayList<Tutor> getTutores() {
        return tutores;
    }
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}