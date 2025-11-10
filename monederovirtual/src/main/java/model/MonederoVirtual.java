package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonederoVirtual implements TransaccionProgramada{
    private String nombre;
    private ArrayList<Cliente> listaClientes;
    private List<Transaccion> transProgramadas;
    private Map<String, LocalDate> fechasProgramadas = new HashMap<>();

    public MonederoVirtual(String nombre) {
        this.nombre = nombre;
        this.listaClientes = new ArrayList<>();
        this.transProgramadas = new ArrayList<>();
    }
    @Override
    public void programarTransaccion(Transaccion transaccion, LocalDate fechaEjecucion) {
        transProgramadas.add(transaccion);
        fechasProgramadas.put(transaccion.getRegistro(), fechaEjecucion);
    }

    @Override
    public void ejecutarTransaccionesProgramadas() {
        LocalDate hoy = LocalDate.now();

        for (Transaccion t : transProgramadas) {
            LocalDate fecha = fechasProgramadas.get(t.getRegistro());

            if (fecha != null && fecha.isEqual(hoy)) {
                t.ejecutar(this);
            }
        }
    }

    @Override
    public void cancelarTransaccionProgramada(String registro) {
        transProgramadas.removeIf(t -> t.getRegistro().equals(registro));
        fechasProgramadas.remove(registro);
    }

}
