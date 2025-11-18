package model;


public class Retiro extends Transaccion{
    private Cuenta cuentaOrigen;

    public Retiro(double valor, Cliente cliente, String concepto,Cuenta cuentaOrigen) {
        super(valor, cliente,concepto);
        this.cuentaOrigen = cuentaOrigen;
        this.puntosGanados = (int)(valor / 100) * 2;
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

}
