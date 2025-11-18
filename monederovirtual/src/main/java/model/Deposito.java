package model;

public class Deposito extends Transaccion{
    private Cuenta cuentaDestino;

    public Deposito(double valor, Cliente cliente,String concepto,Cuenta cuentaDestino) {
        super(valor, cliente,concepto);
        this.cuentaDestino = cuentaDestino;
        this.puntosGanados = (int)(valor / 100);
    }


    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void ejecutar(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        cuentaDestino.depositarDinero(valor);
    }
    @Override
    public String getDescripcion() {
        return "Deposito de: " + valor + " en fecha " + fecha;
    }
}
