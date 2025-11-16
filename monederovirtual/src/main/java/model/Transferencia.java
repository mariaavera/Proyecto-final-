package model;

import java.time.LocalDate;

public class Transferencia extends Transaccion{
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transferencia(double monto, Cliente cliente,
                         Cuenta cuentaOrigen, Cuenta cuentaDestino) {

        super(monto, cliente);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }


    @Override
    public void ejecutar(MonederoVirtual monedero) {
        cuentaOrigen.transferirDinero(cuentaDestino, valor);
    }
    @Override
    public String getDescripcion() {
        return "Transferencia de: " + valor + " el " + fecha+",enviado a la cuenta: "+cuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
