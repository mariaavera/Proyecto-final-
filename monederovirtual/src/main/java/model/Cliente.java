package model;

public class Cliente {
        private String nombre;
        private Cuenta cuenta;
        private int puntos;

    public Cliente(String nombre, Cuenta cuenta, int puntos) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
