package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Multimonedero extends MonederoVirtual implements TransaccionProgramada{
    private List<MonederoVirtual> monederos;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Transaccion transaccion;

    public Multimonedero(String id, double valor) {
        super(id, valor);
        this.monederos=new ArrayList<>();
    }
    public void agregarMonedero(MonederoVirtual m) {
        monederos.add(m);
    }

    public List<MonederoVirtual> getMonederos() {
        return new ArrayList<>(monederos);
    }
    @Override
    public void programarTransaccion(Transaccion transaccion, LocalDate fechaEjecucion) {
        transProgramadas.add(transaccion);
        fechasProgramadas.put(transaccion.getId(), fechaEjecucion);

        System.out.println("Transacción programada para el " + fechaEjecucion);
        new Thread(() -> {
            try {
                while (true) {
                    LocalDate hoy = LocalDate.now();
                    if (hoy.isEqual(fechaEjecucion)) {
                        transaccion.ejecutar(cuentaOrigen, cuentaDestino);
                        System.out.println("Transacción ejecutada automáticamente el " + hoy);
                        break;
                    }
                    Thread.sleep(1000 * 60 * 60 * 24);
                }
            } catch (InterruptedException e) {
                System.out.println("⏹ Transacción cancelada o interrumpida");
            }
        }).start();
    }


    @Override
    public void cancelarTransaccionProgramada(String registro) {
        transProgramadas.removeIf(t -> t.getId().equals(registro));
        fechasProgramadas.remove(registro);
    }

}
