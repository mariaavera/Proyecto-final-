package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MonederoVirtual {
    protected String nombre;
    protected double saldo;
    protected ArrayList<Cliente> listaClientes;
    protected List<Transaccion> transProgramadas;
    protected Map<String, LocalDate> fechasProgramadas = new HashMap<>();
    protected Cuenta cuentaPrincipal;
    public Cuenta getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public MonederoVirtual(String nombre,double valor) {
        this.nombre = nombre;
        this.saldo=valor;
        this.listaClientes = new ArrayList<>();
        this.transProgramadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }


    public abstract void programarTransaccion(Transaccion transaccion, LocalDate fechaEjecucion);
    public abstract void cancelarTransaccionProgramada(String registro);
}
