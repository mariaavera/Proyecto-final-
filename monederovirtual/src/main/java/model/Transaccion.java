package model;

import java.time.LocalDate;

public abstract class Transaccion {
    protected  String registro;
    protected  double valor;
    protected  LocalDate fecha;

    public Transaccion(String registro,double valor, LocalDate fecha ) {
        this.registro = registro;
        this.fecha = fecha;
        this.valor = valor;
    }
    @Override
    public String toString() {
        return registro + " | " + valor + " | " + fecha;
    }
    public abstract void ejecutar();
}
