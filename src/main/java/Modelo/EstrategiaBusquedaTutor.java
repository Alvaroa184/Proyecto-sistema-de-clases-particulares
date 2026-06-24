package Modelo;
import java.util.ArrayList;
public interface EstrategiaBusquedaTutor {
    ArrayList<Tutor> buscar(ArrayList<Tutor> tutores);

    public interface EstrategiaBusqueda {
        ArrayList<Tutor> buscar(ArrayList<Tutor>tutores);
    }
}
