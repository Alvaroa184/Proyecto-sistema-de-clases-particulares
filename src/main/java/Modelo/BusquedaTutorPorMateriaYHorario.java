package Modelo;

import java.util.ArrayList;

public class BusquedaTutorPorMateriaYHorario implements EstrategiaBusquedaTutor {

    private Materia materia;
    private Horario horario;

    public BusquedaTutorPorMateriaYHorario(Materia materia, Horario horario) {
        this.materia = materia;
        this.horario = horario;
    }

    @Override
    public ArrayList<Tutor> buscar(ArrayList<Tutor> tutores) {
        ArrayList<Tutor> resultado = new ArrayList<>();

        for (Tutor tutor : tutores) {
            if (tutor.imparteMateria(materia) && tutor.Disponible(horario)) {
                resultado.add(tutor);
            }
        }

        return resultado;
    }
}