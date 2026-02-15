package model;

import java.time.LocalDate;

public class BeneficioRetiro extends Beneficio implements Puntos{
    public BeneficioRetiro() {
        super("1 mes sin cargos por retiros", 500);
    }

    /**Sobre escribo el metodo aplicar beneficio
     *para ejecutarlo despues 123
     * @param cliente
     */
    @Override
    public void aplicarBeneficio(Cliente cliente) {
        cliente.setRetirosGratisHasta(LocalDate.now().plusMonths(1));
        System.out.println("Aplicado: Retiros gratis por 1 mes.");
    }
}
