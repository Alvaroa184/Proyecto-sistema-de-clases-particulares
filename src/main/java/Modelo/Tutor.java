package Modelo;

import java.util.ArrayList;

public class Tutor {
    private String nombre;
    private String correo;
    private double tarifa;
    private int maximoestudiantes;
    private ArrayList<Materia> materias;
    private ArrayList<Horario> horariosdisponibles;

    public Tutor(String nombre, String correo, double tarifa, int maximoestudiantes) {
        this.nombre = nombre;
        this.correo = correo;
        this.tarifa = tarifa;
        this.maximoestudiantes = maximoestudiantes;
        this.materias = new ArrayList<>();
        this.horariosdisponibles = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public double getTarifa() {
        return tarifa;
    }
    public int getMaximoestudiantes() {
        return maximoestudiantes;
    }
    public ArrayList<Materia> getMaterias() {
        return materias;
    }
    public ArrayList<Horario> getHorariosdisponibles() {
        return horariosdisponibles;
    }
    public void agregarMateria(Materia materia) {
        this.materias.add(materia);
    }
    public void agregarHorario(Horario horario) {
        this.horariosdisponibles.add(horario);
    }
    public boolean Disponible(Horario horario) {
        return this.horariosdisponibles.contains(horario);
    }
    @Override
    public String toString() {
        return nombre;
    }
}
