package model;

import java.time.LocalDate;

public class Deposito extends Transaccion{
    private Cuenta cuentaDestino;

    public Deposito(String registro,double valor, LocalDate fecha, Cuenta cuentaDestino) {
        super(registro,valor,fecha);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void ejecutar() {
        cuentaDestino.depositarDinero(valor);
    }
}
