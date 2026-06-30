package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class Vista_principal extends JFrame {
    private JButton[][] botonesHorario;
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
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        botonesHorario = new JButton[5][5];
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        JPanel panelHorario = new JPanel();
        panelHorario.setLayout(new GridLayout(6, 6, 5, 5));
        panelHorario.setBorder(BorderFactory.createTitledBorder("Horario"));

        JButton btnTutor = new JButton("Gestionar Tutores");
        JButton btnEstudiante = new JButton("Gestionar Estudiantes");
        JButton btnReserva = new JButton("Gestionar Reservas");
        JButton btnSalir = new JButton("Salir");
        panelBotones.add(btnTutor);
        panelBotones.add(btnEstudiante);
        panelBotones.add(btnReserva);
        panelBotones.add(btnSalir);

        panelHorario.add(new JLabel(""));

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            panelHorario.add(new JLabel(dia, SwingConstants.CENTER));
        }
        String[] horas = {"08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00"};
        for (int fila = 0; fila < horas.length; fila++) {
            panelHorario.add(new JLabel(horas[fila], SwingConstants.CENTER));

            for (int columna = 0; columna < dias.length; columna++) {
                JButton bloque = new JButton("");
                botonesHorario[fila][columna] = bloque;
                panelHorario.add(bloque);
            }
        }
        add(panelBotones, BorderLayout.EAST);
        add(panelHorario, BorderLayout.CENTER);

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