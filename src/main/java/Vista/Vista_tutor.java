package Vista;

import Controlador.*;
import Modelo.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import javax.swing.*;
import java.awt.*;

public class Vista_tutor extends JFrame {

    private Tutor_con tutorCon;

    public Vista_tutor(Tutor_con tutorCon) {
        this.tutorCon = tutorCon;

        setTitle("Gestión de Tutores");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnAgregar = new JButton("Añadir tutor");
        JButton btnBuscar = new JButton("Buscar tutor");
        JButton btnEditar = new JButton("Editar tutor");
        JButton btnSalir = new JButton("Salir");

        add(btnAgregar);
        add(btnBuscar);
        add(btnEditar);
        add(btnSalir);

        btnAgregar.addActionListener(e -> {

            JTextField txtNombre = new JTextField();
            JTextField txtCorreo = new JTextField();
            JTextField txtTarifa = new JTextField();
            JTextField txtMaximo = new JTextField();
            JTextField txtMateria = new JTextField();
            JComboBox<String> cbDia = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"});
            JComboBox<String> cbHora = new JComboBox<>(new String[]{"08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00"});
            Object[] mensaje = {"Nombre:", txtNombre, "Correo:", txtCorreo, "Tarifa:", txtTarifa, "Máximo estudiantes:", txtMaximo, "Materia:", txtMateria, "Día disponible:", cbDia, "Hora disponible:", cbHora};

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Añadir Tutor", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                double tarifa = Double.parseDouble(txtTarifa.getText());
                int maximo = Integer.parseInt(txtMaximo.getText());
                String materia = txtMateria.getText();
                Tutor tutor = FabricaPersona.crearTutor(nombre, correo, tarifa, maximo);
                tutor.agregarMateria(new Materia(materia));
                Horario horario = crearHorario(
                        cbDia.getSelectedItem().toString(),
                        cbHora.getSelectedItem().toString()
                );
                tutor.agregarHorario(horario);
                tutorCon.agregarTutor(tutor);
                JOptionPane.showMessageDialog(this, "Tutor agregado correctamente.");
            }});

        btnSalir.addActionListener(e -> {
            dispose();
        });
    }
    private DayOfWeek convertirDia(String diaTexto) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equals(diaTexto)) {
                return DayOfWeek.of(i + 1);
            }
        }
        return DayOfWeek.MONDAY;
    }
    private Horario crearHorario(String diaTexto, String horaTexto) {
        DayOfWeek dia = convertirDia(diaTexto);
        switch (horaTexto) {
            case "08:00-10:00": return new Horario(dia, LocalTime.of(8,0), LocalTime.of(10,0));
            case "10:00-12:00": return new Horario(dia, LocalTime.of(10,0), LocalTime.of(12,0));
            case "12:00-14:00": return new Horario(dia, LocalTime.of(12,0), LocalTime.of(14,0));
            case "14:00-16:00": return new Horario(dia, LocalTime.of(14,0), LocalTime.of(16,0));
            default: return new Horario(dia, LocalTime.of(16,0), LocalTime.of(18,0));
        }
    }
}