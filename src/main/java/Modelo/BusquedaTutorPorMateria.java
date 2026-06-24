package Modelo;

import java.util.ArrayList;

public class BusquedaTutorPorMateria implements EstrategiaBusquedaTutor{
    private Materia materia;

    public BusquedaTutorPorMateria(Materia materia){
        this.materia = materia;
    }
    @Override
    public ArrayList<Tutor> buscar(ArrayList<Tutor> tutores){
        ArrayList<Tutor> resultado = new ArrayList<>();
        for(Tutor tutor: tutores){
            if(tutor.getMaterias().contains(materia)){
                resultado.add(tutor);
            }
        }
        return resultado;
    }
}
