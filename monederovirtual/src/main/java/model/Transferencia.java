package model;

import java.time.LocalDate;

public class Transferencia extends Transaccion{
    private Cuenta origen;
    private Cuenta destino;

    public Transferencia(String registro,double valor, LocalDate fecha, Cuenta origen, Cuenta destino) {
        super(registro,valor, fecha);
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        origen.transferirDinero(destino, valor);
    }
    @Override
    public String getDescripcion() {
        return "Transferencia de: " + valor + " el " + fecha+",enviado a la cuenta: "+destino;
    }

}
