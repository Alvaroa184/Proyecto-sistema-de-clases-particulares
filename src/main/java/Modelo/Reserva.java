package Modelo;

public class Reserva {
    private Estudiante estudiante;
    private Tutor tutor;
    private Materia materia;
    private Horario horario;

    public Reserva( Estudiante estudiante, Tutor tutor, Materia materia, Horario horario) {
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Tutor getTutor() {
        return tutor;
    }

    public Horario getHorario() {
        return horario;
    }
    public Materia getMateria() {
        return materia;
    }
    @Override
    public String toString() {
        return materia + " - " + tutor.getNombre() + " - " + horario;
    }
}
