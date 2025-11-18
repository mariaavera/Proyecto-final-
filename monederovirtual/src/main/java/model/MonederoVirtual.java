package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MonederoVirtual implements TransaccionProgramada{
    protected String nombre;
    protected double saldo;
    protected ArrayList<Cliente> listaClientes;
    protected List<Transaccion> transProgramadas;
    protected Map<String, LocalDate> fechasProgramadas = new HashMap<>();

    public MonederoVirtual(String nombre, double valor) {
        this.nombre = nombre;
        this.saldo=valor;
        this.listaClientes = new ArrayList<>();
        this.transProgramadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
}
