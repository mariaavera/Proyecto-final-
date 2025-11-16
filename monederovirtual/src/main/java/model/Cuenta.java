package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private double saldo;
    private String id;
    private Cliente cliente;
    private ArrayList<Transaccion> listaTransacciones;

    public Cuenta(double saldo, String numero) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.saldo = saldo;
        this.id = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public boolean depositarDinero(double valorADepositar){
        if(valorADepositar<0){
            System.out.println("El valor a depositar tiene que ser positivo");
            return false;
        }
        saldo+=valorADepositar;
        int puntosGanados = (int)(valorADepositar / 100) * 1;
        cliente.agregarPuntos(puntosGanados);
        System.out.println("Deposito exitoso,su dinero se ingreso a la cuenta correctamente");
        System.out.println("Tus puntos ganados fueron: "+puntosGanados);
        String concepto = "Depósito a monedero principal";
        listaTransacciones.add(new Deposito(valorADepositar, cliente, concepto,this));
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
            int puntosGanados = (int)(valorARetirar / 100) * 2;
            cliente.agregarPuntos(puntosGanados);
            System.out.println("Retiro exitoso");
            System.out.println("Puntos ganados: "+puntosGanados);
            String concepto = "Retiro del monedero principal";
            listaTransacciones.add(new Retiro(valorARetirar, cliente, concepto,this));
            return true;
        }
    }
    public boolean transferirDinero(Cuenta destino, double valor) {
        if (destino == null) {
            System.out.println("La cuenta destino no existe.");
            return false;
        }
        if (destino == this) {
            System.out.println("No puede transferirse a la misma cuenta.");
            return false;
        }
        if (valor <= 0) {
            System.out.println("El valor a transferir debe ser positivo.");
            return false;
        }
        if (this.saldo < valor) {
            System.out.println("Saldo insuficiente.");
            return false;
        }
        this.saldo -= valor;
        destino.saldo += valor;
        int puntosGanados = (int)(valor / 100) * 3;
        cliente.agregarPuntos(puntosGanados);
        if (this.cliente != null) {
            this.cliente.setPuntos(this.cliente.getPuntos() + puntosGanados);
        }
        System.out.println("Transferencia realizada. Puntos ganados: " + puntosGanados);
        String concepto = "Transferencia del monedero principal a otra cuenta";
        listaTransacciones.add(new Transferencia(valor, cliente, concepto,this,destino));
        return true;
    }

    public double consultaSaldo(){
        return saldo;
    }
    public ArrayList<Transaccion> consultaTransacciones(){
        ArrayList<Transaccion> historial=new ArrayList<>();
        if (listaTransacciones.isEmpty()) {
            System.out.println("No tienes transacciones registradas.");
            return null;
        }
        System.out.println("Historial de transacciones:");
        for (Transaccion t : listaTransacciones) {
            historial.add(t);
        }
        return historial;
    }
}

