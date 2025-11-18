package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Transaccion {
    protected double valor;
    protected LocalDate fecha;
    protected String id;
    protected Cliente cliente;
    protected String concepto;
    protected int puntosGanados;

    public Transaccion(double valor,Cliente cliente,String concepto) {
        this.valor = valor;
        this.fecha = LocalDate.now();
        this.id = generarId();
        this.cliente = cliente;
        this.concepto=concepto;
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private String generarId() {
        return "TX-" + System.currentTimeMillis();
    }
    @Override
    public String toString() {
        return id + " | " + valor + " | " + fecha;
    }
    public abstract void ejecutar(Cuenta cuentaOrigen, Cuenta cuentaDestino);
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
