package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Cliente;
import model.Cuenta;

public class ConsultaSaldoController {
    @FXML
    private Label lblSaldo;

    private Cuenta cuenta;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        inicializarDatos(cuenta);
    }
    public void inicializarDatos(Cuenta cuenta){
        this.cuenta = cuenta;
        lblSaldo.setText("Saldo actual: " + cuenta.getSaldo());
    }
}
