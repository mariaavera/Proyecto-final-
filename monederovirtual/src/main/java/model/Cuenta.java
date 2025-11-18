package model;
import java.util.ArrayList;

public class Cuenta {
    private double saldo;
    private String id;
    private Cliente OwnedByCliente;
    private ArrayList<Transaccion> listaTransacciones;

    public Cuenta(double saldo, String numero,Cliente cliente) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.saldo = saldo;
        this.id = numero;
        this.OwnedByCliente = cliente;
        this.listaTransacciones = new ArrayList<>();
    }


    public Cliente getCliente() {
        return OwnedByCliente;
    }

    public void setCliente(Cliente cliente) {
        this.OwnedByCliente = cliente;
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
    public String depositarDinero(double valorADepositar){
        if(valorADepositar < 0){
            return "El valor a depositar debe ser positivo.";
        }
        saldo += valorADepositar;
        int puntosGanados = (int)(valorADepositar / 100);
        OwnedByCliente.agregarPuntos(puntosGanados);
        if (listaTransacciones == null) {
            listaTransacciones = new ArrayList<>();
        }
        listaTransacciones.add(
                new Deposito(valorADepositar, OwnedByCliente, "Depósito a monedero principal", this)
        );
        return "Depósito exitoso.\nPuntos ganados: " + puntosGanados;
    }
    public String retirarDinero(double valorARetirar){
        if(valorARetirar<0){
            return "El valor a retirar debe ser positivo.";
        }else if(saldo<valorARetirar){
            return"No tiene saldo suficiente";
        }else{
            saldo-=valorARetirar;
            int puntosGanados = (int)(valorARetirar / 100) * 2;
            OwnedByCliente.agregarPuntos(puntosGanados);
            String concepto = "Retiro del monedero principal";
            listaTransacciones.add(new Retiro(valorARetirar, OwnedByCliente, concepto,this));
            return "Retiro exitoso.\nPuntos ganados: " + puntosGanados;
        }
    }
    public String transferirDinero(Cuenta destino, double valor) {
        if (destino == null) return "La cuenta destino no existe.";
        if (destino == this) return "No puede transferirse a la misma cuenta.";
        if (valor <= 0) return "El valor a transferir debe ser mayor a 0.";
        if (this.saldo < valor) return "Saldo insuficiente.";
        this.saldo -= valor;
        destino.saldo += valor;
        int puntosGanados = (int)(valor / 100) * 3;
        String concepto = "Transferencia al monedero principal";
        if (OwnedByCliente != null) OwnedByCliente.agregarPuntos(puntosGanados);
        listaTransacciones.add(new Transferencia(valor, OwnedByCliente, concepto,this,destino));
        return String.format("Transferencia exitosa. Monto: %.2f."+"\n"+" Puntos ganados: %d. Puntos totales: %d",
                valor, puntosGanados, OwnedByCliente != null ? OwnedByCliente.getPuntos() : 0);
    }
    public double consultaSaldo(){
        return saldo;
    }
    public ArrayList<Transaccion> consultaTransacciones(){
        ArrayList<Transaccion> historial = new ArrayList<>();

        if (listaTransacciones == null) {
            listaTransacciones = new ArrayList<>();
        }
        if (listaTransacciones.isEmpty()) {
            System.out.println("No tienes transacciones registradas.");
            return historial;
        }

        historial.addAll(listaTransacciones);
        return historial;
    }
    @Override
    public String toString() {
        return "Cuenta #" + id + " - Saldo: " + saldo;
    }
}

