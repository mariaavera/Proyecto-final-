package model;

public class BeneficioBonoSaldo extends Beneficio implements Puntos{
    public BeneficioBonoSaldo() {
        super("Bono de 50 unidades", 1000);
    }
    @Override
    public void aplicarBeneficio(Cliente cliente) {

        Transaccion bono = new Deposito(
                50.0,
                cliente,
                "Bono de 50 unidades",
                cliente.getCuenta()
        );

        bono.ejecutar(null, null);
    }
}
