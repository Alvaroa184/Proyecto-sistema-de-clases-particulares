package Controlador;

import Modelo.*;
import java.util.ArrayList;
public class reserva_con {
    private SistemaDeReserva sistema;

    public reserva_con(SistemaDeReserva sistema) {
        this.sistema = sistema;
    }

    public boolean crearReserva(Estudiante estudiante, Tutor tutor, Materia materia, Horario horario) {
        return sistema.crearReserva(estudiante, tutor, materia, horario);
    }
    public boolean cancelarReserva(Estudiante estudiante, Tutor tutor, Horario horario) {
        return sistema.cancelarReserva(estudiante, tutor, horario);
    }
    public void eliminarReserva(Reserva reserva) {
        sistema.eliminarReserva(reserva);
    }

    public ArrayList<Reserva> getReservas() {
        return sistema.getReservas();
    }
}
