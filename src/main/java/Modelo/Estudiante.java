package Modelo;
import java.util.ArrayList;
public class Estudiante {
    private String nombre;
    private String correo;
    private ArrayList<Reserva> reservas;

    public Estudiante(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.reservas = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public void agregarReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
    @Override
    public String toString() {
        return nombre;
    }
}
