package Vista;

import Modelo.*;
import Controlador.*;
import javax.swing.*;
import java.awt.*;

public class Vista_estudiante extends JFrame {

    private Estudiante_con estudianteCon;

    public Vista_estudiante(Estudiante_con estudianteCon) {
        this.estudianteCon = estudianteCon;

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

        btnAgregar.addActionListener(e -> {

            JTextField txtNombre = new JTextField();
            JTextField txtCorreo = new JTextField();

            Object[] mensaje = {"Nombre:", txtNombre, "Correo:", txtCorreo
            };
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Añadir Estudiante", JOptionPane.OK_CANCEL_OPTION
            );
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                FabricaPersona fabrica = new FabricaPersona();
                Estudiante estudiante = fabrica.crearEstudiante(nombre, correo);
                estudianteCon.agregarEstudiante(estudiante);
                JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");
            }
        });
        btnSalir.addActionListener(e -> {
            dispose();
        });
    }

}