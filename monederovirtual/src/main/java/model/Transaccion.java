package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Transaccion {
    protected double valor;
    protected LocalDate fecha;
    protected String id;
    protected Cliente cliente;

    public Transaccion(double valor,Cliente cliente) {
        this.valor = valor;
        this.fecha = LocalDate.now();
        this.id = generarId();
        this.cliente = cliente;
    }
    private String generarId() {
        return "TX-" + System.currentTimeMillis();
    }
    @Override
    public String toString() {
        return id + " | " + valor + " | " + fecha;
    }
    public abstract void ejecutar(MonederoVirtual monedero);
    public abstract String getDescripcion();

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
