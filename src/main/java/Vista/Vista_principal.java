package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vista_principal extends JFrame {
    private JButton[][] botonesHorario;
    private SistemaDeReserva sistema;
    private Tutor_con tutorCon;
    private Estudiante_con estudianteCon;
    private reserva_con reservaCon;
    private JButton btnLimpiarVista;
    private ArrayList<Reserva> reservasFiltradas = null;

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
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));

        JPanel panelHorario = new JPanel();
        panelHorario.setLayout(new GridLayout(6, 6, 5, 5));
        panelHorario.setBorder(BorderFactory.createTitledBorder("Horario"));

        JButton btnTutor = new JButton("Gestionar Tutores");
        JButton btnEstudiante = new JButton("Gestionar Estudiantes");
        JButton btnReserva = new JButton("Gestionar Reservas");
        btnLimpiarVista = new JButton("Limpiar vista");
        btnLimpiarVista.setVisible(false);
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnTutor);
        panelBotones.add(btnEstudiante);
        panelBotones.add(btnReserva);
        panelBotones.add(btnLimpiarVista);
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
                int f = fila;
                int c = columna;
                bloque.addActionListener(e -> mostrarInformacionReserva(f, c));
                botonesHorario[fila][columna] = bloque;
                panelHorario.add(bloque);
            }
        }
        add(panelBotones, BorderLayout.EAST);
        add(panelHorario, BorderLayout.CENTER);

        btnTutor.addActionListener(e -> {
            new Vista_tutor(tutorCon, this).setVisible(true);
        });

        btnEstudiante.addActionListener(e -> {
            new Vista_estudiante(estudianteCon, this).setVisible(true);
        });

        btnReserva.addActionListener(e -> {
            new Vista_reserva(reservaCon, tutorCon, estudianteCon, this).setVisible(true);
        });

        btnLimpiarVista.addActionListener(e -> limpiarVista());

        btnSalir.addActionListener(e -> {
            dispose();
        });
    }
    public void actualizarHorario() {
        limpiarHorario();

        for (Reserva reserva : sistema.getReservas()) {
            Horario horario = reserva.getHorario();

            int fila = (horario.getHoraInicio().getHour() - 8) / 2;
            int columna = horario.getDia().getValue() - 1;

            botonesHorario[fila][columna].setText(reserva.getMateria().getNombre());
        }
    }
    public void mostrarReservasFiltradas(ArrayList<Reserva> reservas) {
        reservasFiltradas = reservas;
        limpiarHorario();

        for (Reserva reserva : reservasFiltradas) {
            Horario horario = reserva.getHorario();

            int fila = (horario.getHoraInicio().getHour() - 8) / 2;
            int columna = horario.getDia().getValue() - 1;

            botonesHorario[fila][columna].setText(reserva.getMateria().getNombre());
        }
        btnLimpiarVista.setVisible(true);
    }

    public void mostrarHorario(Object persona) {
        reservasFiltradas = null;
        limpiarHorario();
        if (persona instanceof Tutor tutor) {
            for (int i = 0; i < tutor.getMaterias().size(); i++) {
                Materia materia = tutor.getMaterias().get(i);
                Horario horario = tutor.getHorariosdisponibles().get(i);
                int fila = (horario.getHoraInicio().getHour() - 8) / 2;
                int columna = horario.getDia().getValue() - 1;
                botonesHorario[fila][columna].setText(materia.getNombre());
            }
        }
        if (persona instanceof Estudiante estudiante) {
            reservasFiltradas = estudiante.getReservas();
            for (Reserva reserva : estudiante.getReservas()) {
                Horario horario = reserva.getHorario();
                int fila = (horario.getHoraInicio().getHour() - 8) / 2;
                int columna = horario.getDia().getValue() - 1;
                botonesHorario[fila][columna].setText(reserva.getMateria().getNombre());
            }
        }
        btnLimpiarVista.setVisible(true);
    }

    private void mostrarInformacionReserva(int fila, int columna) {
        StringBuilder info = new StringBuilder();

        ArrayList<Reserva> reservasAMostrar =
                reservasFiltradas == null ? sistema.getReservas() : reservasFiltradas;

        for (Reserva reserva : reservasAMostrar) {
            Horario horario = reserva.getHorario();

            if ((horario.getHoraInicio().getHour() - 8) / 2 == fila &&
                    horario.getDia().getValue() - 1 == columna) {

                if (info.isEmpty()) {
                    info.append("Materia: ").append(reserva.getMateria()).append("\n");
                    info.append("Tutor: ").append(reserva.getTutor().getNombre()).append("\n");
                    info.append("Correo tutor: ").append(reserva.getTutor().getCorreo()).append("\n");
                    info.append("Tarifa: $").append(reserva.getTutor().getTarifa()).append("\n");
                    info.append("Horario: ").append(reserva.getHorario()).append("\n\n");
                    info.append("Estudiantes:\n");
                }

                info.append("- ")
                        .append(reserva.getEstudiante().getNombre())
                        .append(" (")
                        .append(reserva.getEstudiante().getCorreo())
                        .append(")\n");
            }
        }
        JOptionPane.showMessageDialog(this,
                info.isEmpty() ? "No hay reservas." : info.toString());
    }

    private void limpiarHorario() {
        for (int fila = 0; fila < botonesHorario.length; fila++) {
            for (int columna = 0; columna < botonesHorario[fila].length; columna++) {
                botonesHorario[fila][columna].setText("");
            }
        }
    }
    public void limpiarVista() {
        reservasFiltradas = null;
        actualizarHorario();
        btnLimpiarVista.setVisible(false);
    }
}