package model;

public class BeneficioTransferencia extends Beneficio implements Puntos{
    public BeneficioTransferencia() {
        super("10% de descuento en transferencias", 100);
    }

    @Override
    public void aplicarBeneficio(Cliente cliente) {
        cliente.setDescuentoTransferencias(0.10);
        System.out.println("Aplicado: 10% de descuento en transferencias.");
    }
}

