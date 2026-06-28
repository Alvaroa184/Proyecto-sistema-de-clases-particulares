package Modelo;

import java.util.Objects;

public class Materia {
    private String nombre;

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Materia)) return false;
        Materia otra = (Materia) obj;
        return nombre.equalsIgnoreCase(otra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre;
    }
}