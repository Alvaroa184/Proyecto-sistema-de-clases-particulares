package Vista;

import Controlador.*;

import javax.swing.*;

public class Vista_reserva extends JFrame {

    private reserva_con reservaCon;
    private Tutor_con tutorCon;
    private Estudiante_con estudianteCon;

    public Vista_reserva(reserva_con reservaCon, Tutor_con tutorCon, Estudiante_con estudianteCon) {
        this.reservaCon = reservaCon;
        this.tutorCon = tutorCon;
        this.estudianteCon = estudianteCon;

        setTitle("Gestión de Reservas");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}