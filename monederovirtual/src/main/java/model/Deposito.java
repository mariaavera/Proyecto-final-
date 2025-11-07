package model;

import java.time.LocalDate;

public class Deposito extends Transaccion implements SistemaPuntos{
    public Deposito(String registro, LocalDate fecha, double valor) {
        super(registro, fecha, valor);
    }

}
