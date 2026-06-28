package Vista;

import Controlador.Estudiante_con;

import javax.swing.*;

public class Vista_estudiante extends JFrame {

    private Estudiante_con estudianteCon;

    public Vista_estudiante(Estudiante_con estudianteCon) {
        this.estudianteCon = estudianteCon;

        setTitle("Gestión de Estudiantes");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}