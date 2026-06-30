package Modelo;

public class FabricaPersona {

    public static Estudiante crearEstudiante(String nombre, String correo) {
        return new Estudiante(nombre, correo);
    }

    public static Tutor crearTutor(String nombre, String correo,
                                   double tarifa, int maximoEstudiantes) {

        return new Tutor(nombre, correo, tarifa, maximoEstudiantes);
    }
}