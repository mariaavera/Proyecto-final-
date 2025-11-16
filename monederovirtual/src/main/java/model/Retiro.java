package model;

import java.time.LocalDate;

public class Retiro extends Transaccion{
    private Cuenta cuentaOrigen;

    public Retiro(double valor, Cliente cliente, Cuenta cuentaOrigen) {
        super(valor, cliente);
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    @Override
    public void ejecutar(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        cuentaOrigen.retirarDinero(valor);
    }
    @Override
    public String getDescripcion() {
        return "Retiro de: " + valor + " en fecha " + fecha;
    }
}
