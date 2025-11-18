package model;

public class BeneficioBonoSaldo extends Beneficio implements Puntos{
    public BeneficioBonoSaldo() {
        super("Bono de 50 unidades", 1000);
    }
    @Override
    public void aplicarBeneficio(Cliente cliente) {

        cliente.getCuentas().forEach(cuenta -> {
            cuenta.depositarDinero(50.0);
        });

        System.out.println("Aplicado: Bono de 50 unidades a todas las cuentas.");
    }
}

