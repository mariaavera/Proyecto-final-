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
    public boolean depositarDinero(double valorADepositar){
        if(valorADepositar<0){
            System.out.println("El valor a depositar tiene que ser positivo");
            return false;
        }
        saldo+=valorADepositar;
        System.out.println("Deposito exitoso,su dinero se ingreso a la cuenta correctamente");
        return true;
    }
    public boolean retirarDinero(double valorARetirar){
        if(valorARetirar<0){
            System.out.println("No puede retirar un valor negativo");
            return false;
        }else if(saldo<valorARetirar){
            System.out.println("No tiene saldo suficiente");
            return false;
        }else{
            saldo-=valorARetirar;
            System.out.println("Retiro exitoso");
            return true;
        }
    }
}
