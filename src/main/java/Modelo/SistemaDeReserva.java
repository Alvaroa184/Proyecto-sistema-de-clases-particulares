package Modelo;

import java.util.ArrayList;

public class SistemaDeReserva {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Tutor> tutores = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public SistemaDeReserva() {
        this.reservas = new ArrayList<>();
        this.tutores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }
    public void agregarTutor(Tutor tutor) {
        tutores.add(tutor);
    }
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
    public boolean crearReserva(Estudiante estudiante,Tutor tutor,Materia materia,Horario horario) {
      if(!tutor.getMaterias().contains(materia)){
          return false;
      }
      if(!tutor.Disponible(horario)){
          return false;
      }
      if(ExisteChoqueDeHorario(tutor,horario)){
          return false;
      }
      Reserva reserva=new Reserva(estudiante,tutor,materia,horario);
      reservas.add(reserva);
      estudiante.agregarReserva(reserva);

      return true;
    }
    private boolean ExisteChoqueDeHorario(Tutor tutor,Horario horario) {
        for(Reserva reserva : reservas){
            if(reserva.getTutor().equals(tutor) && reserva.getHorario().Superpone(horario)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public ArrayList<Tutor> getTutores() {
        return tutores;
    }
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}