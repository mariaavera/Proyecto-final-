package model;

public class Transferencia extends Transaccion{
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double comisionBase = 0.02;

    public Transferencia(double monto, Cliente cliente,String concepto,
                         Cuenta cuentaOrigen, Cuenta cuentaDestino) {

        super(monto, cliente,concepto);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.puntosGanados = (int)(valor / 100) * 3;
    }


    @Override
    public void ejecutar(Cuenta cuentaOrigen, Cuenta cuentaDestino) {

        double comision = valor * comisionBase;
        double total = valor + comision;

        if (cuentaOrigen.getSaldo() < total) {
            System.out.println("Saldo insuficiente para la transferencia");
            return;
        }
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - total);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + valor);
        System.out.println("Transferencia realizada con éxito.");
    }

    @Override
    public String getDescripcion() {
        return "Transferencia de: " + valor + " \nel " + fecha+",enviado a la cuenta: "+cuentaDestino;
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
