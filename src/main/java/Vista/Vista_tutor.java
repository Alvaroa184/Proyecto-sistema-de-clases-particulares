package Vista;

import Controlador.Tutor_con;

import javax.swing.*;

public class Vista_tutor extends JFrame {

    private Tutor_con tutorCon;

    public Vista_tutor(Tutor_con tutorCon) {
        this.tutorCon = tutorCon;

        setTitle("Gestión de Tutores");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}