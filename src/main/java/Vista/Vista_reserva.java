package Vista;

import Controlador.*;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;

public class Vista_reserva extends JFrame {

    private reserva_con reservaCon;
    private Tutor_con tutorCon;
    private Estudiante_con estudianteCon;
    private Vista_principal vistaPrincipal;

    public Vista_reserva(reserva_con reservaCon, Tutor_con tutorCon, Estudiante_con estudianteCon, Vista_principal vistaPrincipal) {
        this.reservaCon = reservaCon;
        this.tutorCon = tutorCon;
        this.estudianteCon = estudianteCon;
        this.vistaPrincipal = vistaPrincipal;

        setTitle("Gestión de Reservas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnCrear = new JButton("Crear reserva");
        JButton btnCancelar = new JButton("Cancelar reserva");
        JButton btnSalir = new JButton("Salir");

        add(btnCrear);
        add(btnCancelar);
        add(btnSalir);

        btnCrear.addActionListener(e -> crearReserva());
        btnSalir.addActionListener(e -> dispose());

        //triple t sahur <- si borras esto se rompe  todo el codigo
    }
    private void crearReserva() {

        JComboBox<Estudiante> cbEstudiante = new JComboBox<>();
        JComboBox<Tutor> cbTutor = new JComboBox<>();
        JComboBox<Materia> cbMateria = new JComboBox<>();
        JComboBox<Horario> cbHorario = new JComboBox<>();

        JButton btnAgregarEstudiante = new JButton("Agregar estudiante");

        DefaultListModel<Estudiante> modeloInscritos = new DefaultListModel<>();
        JList<Estudiante> listaInscritos = new JList<>(modeloInscritos);

        for (Estudiante estudiante : estudianteCon.getEstudiantes()) {
            cbEstudiante.addItem(estudiante);
        }

        for (Tutor tutor : tutorCon.getTutores()) {
            cbTutor.addItem(tutor);
        }

        cbTutor.addActionListener(e ->
                cargarDatosTutor((Tutor) cbTutor.getSelectedItem(), cbMateria, cbHorario)
        );

        if (cbTutor.getItemCount() > 0) {
            cargarDatosTutor((Tutor) cbTutor.getSelectedItem(), cbMateria, cbHorario);
        }

        btnAgregarEstudiante.addActionListener(e -> {
            Estudiante estudiante = (Estudiante) cbEstudiante.getSelectedItem();

            if (estudiante != null && !modeloInscritos.contains(estudiante)) {
                modeloInscritos.addElement(estudiante);
            }
        });
        Object[] datos = {"Tutor:", cbTutor, "Materia:", cbMateria, "Horario:", cbHorario, "Estudiante:", cbEstudiante, "", btnAgregarEstudiante, "Inscritos:", new JScrollPane(listaInscritos)};

        if (JOptionPane.showConfirmDialog(this, datos, "Crear Reserva",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            Tutor tutor = (Tutor) cbTutor.getSelectedItem();
            Materia materia = (Materia) cbMateria.getSelectedItem();
            Horario horario = (Horario) cbHorario.getSelectedItem();

            if (modeloInscritos.isEmpty() || tutor == null || materia == null || horario == null) {
                JOptionPane.showMessageDialog(this, "Debe agregar al menos un estudiante, un tutor, una materia y un horario.");
                return;
            }
            boolean todasCreadas = true;

            for (int i = 0; i < modeloInscritos.size(); i++) {
                Estudiante estudiante = modeloInscritos.get(i);

                if (!reservaCon.crearReserva(estudiante, tutor, materia, horario)) {
                    todasCreadas = false;
                }
            }

            vistaPrincipal.actualizarHorario();

            JOptionPane.showMessageDialog(this, todasCreadas ? "Reservas creadas correctamente." : "Algunas reservas no se pudieron crear.");
        }
    }

    private void cargarDatosTutor(Tutor tutor, JComboBox<Materia> cbMateria, JComboBox<Horario> cbHorario) {
        cbMateria.removeAllItems();
        cbHorario.removeAllItems();
        if (tutor == null) return;
        for (Materia materia : tutor.getMaterias()) {
            cbMateria.addItem(materia);
        }
        for (Horario horario : tutor.getHorariosdisponibles()) {
            cbHorario.addItem(horario);
        }
    }
}