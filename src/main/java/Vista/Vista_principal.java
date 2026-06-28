package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class Vista_principal extends JFrame {

    private SistemaDeReserva sistema;
    private Tutor_con tutorCon;
    private Estudiante_con estudianteCon;
    private reserva_con reservaCon;

    public Vista_principal() {
        sistema = new SistemaDeReserva();

        tutorCon = new Tutor_con(sistema);
        estudianteCon = new Estudiante_con(sistema);
        reservaCon = new reserva_con(sistema);

        setTitle("Sistema de Reservas de Clases");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnTutor = new JButton("Gestionar Tutores");
        JButton btnEstudiante = new JButton("Gestionar Estudiantes");
        JButton btnReserva = new JButton("Gestionar Reservas");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnTutor);
        panel.add(btnEstudiante);
        panel.add(btnReserva);
        panel.add(btnSalir);

        add(panel);

        btnTutor.addActionListener(e -> {
            new Vista_tutor(tutorCon).setVisible(true);
        });

        btnEstudiante.addActionListener(e -> {
            new Vista_estudiante(estudianteCon).setVisible(true);
        });

        btnReserva.addActionListener(e -> {
            new Vista_reserva(reservaCon, tutorCon, estudianteCon).setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            dispose();
        });
    }
}