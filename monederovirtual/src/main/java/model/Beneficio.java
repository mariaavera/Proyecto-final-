package model;

public abstract class Beneficio {
    private String nombre;
    private int puntosRequeridos;

    public Beneficio(String nombre, int puntosRequeridos) {
        this.nombre = nombre;
        this.puntosRequeridos = puntosRequeridos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    @Override
    public String toString() {
        return nombre + " (requiere " + puntosRequeridos + " puntos)";
    }
}

