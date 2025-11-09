package model;

import java.time.LocalDate;

public class Transaccion {
    private String registro;
    private double valor;
    private LocalDate fecha;

    public Transaccion(String registro,double valor, LocalDate fecha ) {
        this.registro = registro;
        this.fecha = fecha;
        this.valor = valor;
    }
    @Override
    public String toString() {
        return registro + " | " + valor + " | " + fecha;
    }
}
