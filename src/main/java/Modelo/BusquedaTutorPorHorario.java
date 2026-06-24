package Modelo;

import java.util.ArrayList;

public class BusquedaTutorPorHorario implements EstrategiaBusquedaTutor {
    private Horario horario;

    public BusquedaTutorPorHorario(Horario horario) {
        this.horario = horario;
    }
    @Override
    public ArrayList<Tutor> buscar(ArrayList<Tutor>tutores) {
        ArrayList<Tutor> resultado = new ArrayList<>();
        for(Tutor tutor: tutores){
            if(tutor.Disponible(horario)){
                resultado.add(tutor);
            }
        }
        return resultado;
    }
}
