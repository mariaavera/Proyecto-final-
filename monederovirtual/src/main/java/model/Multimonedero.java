package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Multimonedero extends MonederoVirtual implements TransaccionProgramada{
    protected List<MonederoVirtual> monederos;
    public Multimonedero(String id, double valor) {
        super(id, valor);
        this.monederos=new ArrayList<>();
    }
    @Override
    public void programarTransaccion(Transaccion transaccion, LocalDate fechaEjecucion) {
        transProgramadas.add(transaccion);
        fechasProgramadas.put(transaccion.getRegistro(), fechaEjecucion);

        System.out.println("Transacción programada para el " + fechaEjecucion);
        new Thread(() -> {
            try {
                while (true) {
                    LocalDate hoy = LocalDate.now();
                    if (hoy.isEqual(fechaEjecucion)) {
                        transaccion.ejecutar(this);
                        System.out.println("💸 Transacción ejecutada automáticamente el " + hoy);
                        break;
                    }
                    Thread.sleep(1000 * 60 * 60 * 24); // dormir 1 día antes de volver a revisar
                }
            } catch (InterruptedException e) {
                System.out.println("⏹ Transacción cancelada o interrumpida");
            }
        }).start();
    }


    @Override
    public void cancelarTransaccionProgramada(String registro) {
        transProgramadas.removeIf(t -> t.getRegistro().equals(registro));
        fechasProgramadas.remove(registro);
    }
}
