package model;

public class Cuenta {
    private double saldo;
    private String numero;

    public Cuenta(double saldo, String numero) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.saldo = saldo;
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void depositarDinero(double valorADepositar){
        if(valorADepositar<0){
            System.out.println("El valor a depositar tiene que ser positivo");
            return;
        }
        saldo+=valorADepositar;
    }
}
