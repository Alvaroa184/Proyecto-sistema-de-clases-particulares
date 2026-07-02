package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Vista_formularioTutor {

    public static Tutor mostrar(JFrame padre,  Tutor tutorEditar) {
        boolean editando = tutorEditar != null;

        JTextField txtNombre = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtTarifa = new JTextField();
        JTextField txtMaximo = new JTextField();
        JTextField txtMateria = new JTextField();

        JButton btnAgregarMateria = new JButton("Agregar materia");
        JButton btnEliminarMateria = new JButton("Eliminar materia/horario");

        DefaultListModel<Materia> modeloMaterias = new DefaultListModel<>();
        DefaultListModel<Horario> modeloHorarios = new DefaultListModel<>();
        JList<Materia> listaMaterias = new JList<>(modeloMaterias);

        JComboBox<String> cbDia = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"});
        JComboBox<String> cbHora = new JComboBox<>(new String[]{"08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00"});

        if (editando) {
            txtNombre.setText(tutorEditar.getNombre());
            txtCorreo.setText(tutorEditar.getCorreo());
            txtTarifa.setText(String.valueOf(tutorEditar.getTarifa()));
            txtMaximo.setText(String.valueOf(tutorEditar.getMaximoestudiantes()));

            for (int i = 0; i < tutorEditar.getMaterias().size(); i++) {
                modeloMaterias.addElement(tutorEditar.getMaterias().get(i));
                modeloHorarios.addElement(tutorEditar.getHorariosdisponibles().get(i));
            }
        }

        btnAgregarMateria.addActionListener(e -> {
            if (!txtMateria.getText().isBlank()) {
                modeloMaterias.addElement(new Materia(txtMateria.getText()));
                modeloHorarios.addElement(crearHorario(cbDia.getSelectedItem().toString(), cbHora.getSelectedIndex()));
                txtMateria.setText("");
            }
        });

        btnEliminarMateria.addActionListener(e -> {
            int indice = listaMaterias.getSelectedIndex();

            if (indice != -1) {
                modeloMaterias.remove(indice);
                modeloHorarios.remove(indice);
            }
        });

        Object[] mensaje = {"Nombre:", txtNombre, "Correo:", txtCorreo, "Tarifa:", txtTarifa, "Máximo estudiantes:", txtMaximo, "Materia:", txtMateria, "Día disponible:", cbDia, "Hora disponible:", cbHora, "", btnAgregarMateria, "", btnEliminarMateria, "Materias:", new JScrollPane(listaMaterias)};

        int opcion = JOptionPane.showConfirmDialog(padre, mensaje, editando ? "Editar tutor" : "Añadir tutor", JOptionPane.OK_CANCEL_OPTION);
        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }

        if (txtCorreo.getText().isBlank() || !txtCorreo.getText().contains("@")) {
            JOptionPane.showMessageDialog(padre, "Ingresa un correo válido (debe contener @).");
            return null;
        }

        double tarifa;
        int maximo;
        try {
            tarifa = Double.parseDouble(txtTarifa.getText());
            maximo = Integer.parseInt(txtMaximo.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(padre, "Tarifa y máximo de estudiantes deben ser números válidos.");
            return null;
        }
        if (tarifa <= 0) {
            JOptionPane.showMessageDialog(padre, "La tarifa debe ser mayor a 0.");
            return null;
        }
        if (maximo <= 0) {
            JOptionPane.showMessageDialog(padre, "El máximo de estudiantes debe ser mayor a 0.");
            return null;
        }

        Tutor tutor;
        if (editando) {
            tutor = tutorEditar;
            tutor.setNombre(txtNombre.getText());
            tutor.setCorreo(txtCorreo.getText());
            tutor.setTarifa(tarifa);
            tutor.setMaximoestudiantes(maximo);
            tutor.getMaterias().clear();
            tutor.getHorariosdisponibles().clear();
        } else {
            tutor = FabricaPersona.crearTutor(
                    txtNombre.getText(),
                    txtCorreo.getText(),
                    tarifa,
                    maximo
            );
        }

        for (int i = 0; i < modeloMaterias.size(); i++) {
            tutor.agregarMateria(modeloMaterias.get(i));
            tutor.agregarHorario(modeloHorarios.get(i));
        }

        return tutor;
    }

    public static Horario crearHorario(String diaTexto, int bloque) {
        return new Horario(
                convertirDia(diaTexto),
                LocalTime.of(8 + bloque * 2, 0),
                LocalTime.of(10 + bloque * 2, 0)
        );
    }

   public static DayOfWeek convertirDia(String diaTexto) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equals(diaTexto)) {
                return DayOfWeek.of(i + 1);
            }
        }

        return DayOfWeek.MONDAY;
    }
}