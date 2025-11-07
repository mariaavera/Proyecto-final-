package model;

import java.time.LocalDate;

public abstract class Transaccion {
    private String registro;
    private LocalDate fecha;
    private double valor;

    public Transaccion(String registro, LocalDate fecha, double valor) {
        this.registro = registro;
        this.fecha = fecha;
        this.valor = valor;
    }
}
