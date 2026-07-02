package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vista_tutor extends JFrame {

    private Tutor_con tutorCon;
    private Vista_principal vistaPrincipal;

    public Vista_tutor(Tutor_con tutorCon, Vista_principal vistaPrincipal) {
        this.tutorCon = tutorCon;
        this.vistaPrincipal = vistaPrincipal;

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
            Tutor tutor = Vista_formularioTutor.mostrar(this, null);
            if (tutor != null) {
                boolean agregado = tutorCon.agregarTutor(tutor);
                JOptionPane.showMessageDialog(this, agregado
                        ? "Tutor agregado correctamente."
                        : "Ya existe un tutor con ese nombre.");
            }
        });

        btnEditar.addActionListener(e -> {
            Tutor tutor = seleccionarTutor();

            if (tutor != null) {
                String opcion = (String) JOptionPane.showInputDialog(this, "¿Qué quieres hacer con este tutor?", "Editar tutor", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Editar datos", "Eliminar tutor"}, "Editar datos");
                if (opcion == null) {
                    return;
                }
                if (opcion.equals("Editar datos")) {
                    Tutor editado = Vista_formularioTutor.mostrar(this, tutor);

                    if (editado != null) {
                        JOptionPane.showMessageDialog(this, "Tutor editado correctamente.");
                        vistaPrincipal.actualizarHorario();
                    }
                }
                if (opcion.equals("Eliminar tutor")) {
                    int confirmar = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este tutor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {
                        tutorCon.eliminarTutor(tutor);
                        JOptionPane.showMessageDialog(this, "Tutor eliminado correctamente.");
                        vistaPrincipal.actualizarHorario();
                    }
                }
            }
        });
        btnBuscar.addActionListener(e -> buscarTutor());
        btnSalir.addActionListener(e -> dispose());
    }

    private Tutor seleccionarTutor() {
        JComboBox<Tutor> cbTutor = new JComboBox<>();
        for (Tutor tutor : tutorCon.getTutores()) {
            cbTutor.addItem(tutor);
        }
        if (cbTutor.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay tutores registrados.");
            return null;
        }
        int opcion = JOptionPane.showConfirmDialog(this, cbTutor, "Seleccionar tutor", JOptionPane.OK_CANCEL_OPTION);

        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }
        return (Tutor) cbTutor.getSelectedItem();
    }

    private void buscarTutor() {
        String criterio = (String) JOptionPane.showInputDialog(this, "Buscar por:", "Buscar Tutor", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Materia", "Horario", "Materia y horario"}, "Materia");
        if (criterio == null) return;

        String nombreMateria = null;
        Horario horario = null;

        if (!criterio.equals("Horario")) {
            nombreMateria = JOptionPane.showInputDialog(this, "Materia:");
            if (nombreMateria == null || nombreMateria.isBlank()) return;
        }
        if (!criterio.equals("Materia")) {
            JComboBox<String> cbDia = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"});
            JComboBox<String> cbHora = new JComboBox<>(new String[]{"08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00"});
            Object[] datos = {"Día:", cbDia, "Hora:", cbHora};
            int opcion = JOptionPane.showConfirmDialog(this, datos, "Horario", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) {
                return;
            }
            horario = Vista_formularioTutor.crearHorario(cbDia.getSelectedItem().toString(), cbHora.getSelectedIndex());
        }
        ArrayList<Tutor> tutores;

        if (criterio.equals("Materia")) {
            tutores = tutorCon.buscarPorMateria(new Materia(nombreMateria));
        } else if (criterio.equals("Horario")) {
            tutores = tutorCon.buscarPorHorario(horario);
        } else {
            tutores = tutorCon.buscarPorMateriaYHorario(new Materia(nombreMateria), horario);
        }

        if (tutores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron tutores.");
            return;
        }

        JComboBox<Tutor> cbTutor = new JComboBox<>();
        for (Tutor tutor : tutores) {
            cbTutor.addItem(tutor);
        }

        int opcion = JOptionPane.showConfirmDialog(this, cbTutor, "Tutores encontrados", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            Tutor tutor = (Tutor) cbTutor.getSelectedItem();
            if (tutor != null) {
                vistaPrincipal.mostrarHorario(tutor);
            }
        }
    }
}