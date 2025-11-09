package model;

import java.time.LocalDate;

public class Retiro extends Transaccion{
    private Cuenta cuentaOrigen;

    public Retiro(String registro,double valor, LocalDate fecha, Cuenta cuentaOrigen) {
        super(registro,valor,fecha);
        this.cuentaOrigen = cuentaOrigen;
    }

    @Override
    public void ejecutar() {
        cuentaOrigen.retirarDinero(valor);
    }
}
