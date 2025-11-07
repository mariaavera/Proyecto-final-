package model;

import java.util.ArrayList;

public class MonederoVirtual {
    private String nombre;
    private ArrayList<Cliente> listaClientes;

    public MonederoVirtual(String nombre) {
        this.nombre = nombre;
        this.listaClientes = new ArrayList<>();
    }

}
