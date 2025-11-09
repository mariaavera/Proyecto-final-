package model;

import java.util.ArrayList;

public class MonederoVirtual {
    private String nombre;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<TransaccionProgramada> transProgramadas = new ArrayList<>();

    public MonederoVirtual(String nombre) {
        this.nombre = nombre;
        this.listaClientes = new ArrayList<>();
    }
    public void agregarCuenta(Cuenta c) {
        cuentas.add(c);
    }

    public void programarTransaccion(TransaccionProgramada t) {
        transProgramadas.add(t);
    }

    public void procesarTransacciones() {
        if (transProgramadas.isEmpty()) {
            System.out.println("No hay transacciones programadas.");
            return;
        }

        // ordenar por fecha
        Collections.sort(transProgramadas,
                (a, b) -> a.getFechaEjecucion().compareTo(b.getFechaEjecucion()));

        for (TransaccionProgramada t : transProgramadas) {
            ejecutarTransaccion(t);
        }

        transProgramadas.clear();
    }

    private void ejecutarTransaccion(TransaccionProgramada t) {
        if (t.getTipo().equals("DEPOSITO")) {
            t.getDestino().depositarDinero(t.getMonto());
        }
        else if (t.getTipo().equals("TRANSFERENCIA")) {
            t.getOrigen().transferir(t.getDestino(), t.getMonto());
        }

        System.out.println("Ejecutada el " + t.getFechaEjecucion());
    }

}
