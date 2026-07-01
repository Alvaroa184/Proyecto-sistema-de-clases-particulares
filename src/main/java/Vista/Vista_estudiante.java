package Vista;

import Modelo.*;
import Controlador.*;

import javax.swing.*;
import java.awt.*;

public class Vista_estudiante extends JFrame {

    private Estudiante_con estudianteCon;
    private Vista_principal vista_principal;

    public Vista_estudiante(Estudiante_con estudianteCon, Vista_principal vistaPrincipal) {
        this.estudianteCon = estudianteCon;
        this.vista_principal = vistaPrincipal;

        setTitle("Gestión de Estudiantes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnAgregar = new JButton("Añadir estudiante");
        JButton btnBuscar = new JButton("Buscar estudiante");
        JButton btnEditar = new JButton("Editar estudiante");
        JButton btnSalir = new JButton("Salir");

        add(btnAgregar);
        add(btnBuscar);
        add(btnEditar);
        add(btnSalir);

        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnBuscar.addActionListener(e -> buscarEstudiante());
        btnEditar.addActionListener(e -> editarEstudiante());
        btnSalir.addActionListener(e -> dispose());
    }

    private void agregarEstudiante() {
        Estudiante estudiante = mostrarFormularioEstudiante(null);
        if (estudiante != null) {
            estudianteCon.agregarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");
        }
    }
    private void buscarEstudiante() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del estudiante:");
        if (correo == null || correo.isBlank()) {
            return;
        }
        Estudiante estudiante = estudianteCon.buscarEstudiante(correo);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el estudiante.");
            return;
        }
        String info = "Nombre: " + estudiante.getNombre() + "\n"
                + "Correo: " + estudiante.getCorreo() + "\n"
                + "Reservas: " + estudiante.getReservas().size();
        JOptionPane.showMessageDialog(this, info);
        vista_principal.mostrarHorario(estudiante);
    }
    private void editarEstudiante() {
        Estudiante estudiante = seleccionarEstudiante();

        if (estudiante == null) {
            return;
        }

        String opcion = (String) JOptionPane.showInputDialog(this, "¿Qué quieres hacer con este estudiante?", "Editar estudiante", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Editar datos", "Eliminar estudiante"}, "Editar datos");
        if (opcion == null) {
            return;
        }
        if (opcion.equals("Editar datos")) {
            Estudiante editado = mostrarFormularioEstudiante(estudiante);
            if (editado != null) {
                JOptionPane.showMessageDialog(this, "Estudiante editado correctamente.");
            }
        }

        if (opcion.equals("Eliminar estudiante")) {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este estudiante?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                estudianteCon.eliminarEstudiante(estudiante);
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
            }
        }
    }

    private Estudiante seleccionarEstudiante() {
        JComboBox<Estudiante> cbEstudiante = new JComboBox<>();
        for (Estudiante estudiante : estudianteCon.getEstudiantes()) {
            cbEstudiante.addItem(estudiante);
        }
        if (cbEstudiante.getItemCount() == 0) {JOptionPane.showMessageDialog(this, "No hay estudiantes registrados.");
            return null;
        }
        int opcion = JOptionPane.showConfirmDialog(this, cbEstudiante, "Seleccionar estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }
        return (Estudiante) cbEstudiante.getSelectedItem();
    }
    private Estudiante mostrarFormularioEstudiante(Estudiante estudianteEditar) {
        boolean editando = estudianteEditar != null;

        JTextField txtNombre = new JTextField();
        JTextField txtCorreo = new JTextField();
        if (editando) {txtNombre.setText(estudianteEditar.getNombre());txtCorreo.setText(estudianteEditar.getCorreo());
        }
        Object[] mensaje = {"Nombre:", txtNombre, "Correo:", txtCorreo};
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, editando ? "Editar estudiante" : "Añadir estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }
        if (txtNombre.getText().isBlank() || txtCorreo.getText().isBlank()) {JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return null;
        }
        if (editando) {estudianteEditar.setNombre(txtNombre.getText());estudianteEditar.setCorreo(txtCorreo.getText());
            return estudianteEditar;
        }
        return FabricaPersona.crearEstudiante(txtNombre.getText(), txtCorreo.getText());
    }
}