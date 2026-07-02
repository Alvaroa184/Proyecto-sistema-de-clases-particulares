package Controlador;
import java.util.ArrayList;
import Modelo.*;

public class Estudiante_con {

    private SistemaDeReserva sistema;


    public Estudiante_con(SistemaDeReserva sistema) {
        this.sistema = sistema;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {return sistema.agregarEstudiante(estudiante);}

    public Estudiante buscarEstudiante(String correo) {
        return sistema.buscarEstudiante(correo);
    }
    public boolean editarEstudiante(String correo, String nuevoNombre, String nuevoCorreo) {
        return sistema.editarEstudiante(correo, nuevoNombre, nuevoCorreo);
    }
    public ArrayList<Estudiante> getEstudiantes() {
        return sistema.getEstudiantes();
    }
    public void eliminarEstudiante(Estudiante estudiante) {
        sistema.eliminarEstudiante(estudiante);
    }
}