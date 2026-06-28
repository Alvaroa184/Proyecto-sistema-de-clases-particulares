package Modelo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class Horario {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
    public boolean Superpone(Horario otro) {
        if (!this.dia.equals(otro.dia)) {
            return false;
        }

        return this.horaInicio.isBefore(otro.horaFin) &&
                this.horaFin.isAfter(otro.horaInicio);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Horario)) return false;
        Horario otro= (Horario) obj;
        return dia == otro.dia && horaInicio.equals(otro.horaInicio) && horaFin.equals(otro.horaFin);
    }
    @Override
    public int hashCode() {
        return Objects.hash(dia, horaInicio, horaFin);
    }
    @Override
    public String toString() {
        return dia + " " + horaInicio + " " + horaFin;
    }
}