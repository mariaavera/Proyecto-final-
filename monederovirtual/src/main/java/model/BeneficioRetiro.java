package model;

import java.time.LocalDate;

public class BeneficioRetiro extends Beneficio implements Puntos{
    public BeneficioRetiro() {
        super("1 mes sin cargos por retiros", 500);
    }

    @Override
    public void aplicarBeneficio(Cliente cliente) {
        cliente.setRetirosGratisHasta(LocalDate.now().plusMonths(1));
        System.out.println("Aplicado: Retiros gratis por 1 mes.");
    }
}
