package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Cuenta;
import model.MonederoVirtual;

public class DepositarDineroController {

    @FXML private TextField txtDepositoDinero;
    @FXML private Label lblMensaje;

    private Cliente cliente;
    private Cuenta cuentaActiva;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cuentaActiva = cliente.getCuenta();
    }

    @FXML
    public void DepositarDineroaction() {
        System.out.println("Texto actual = '" + txtDepositoDinero.getText() + "'");
        try {
            double monto = Double.parseDouble(txtDepositoDinero.getText());
            cuentaActiva.depositarDinero(monto);
            lblMensaje.setText("Depósito exitoso");
        } catch (Exception e) {
            lblMensaje.setText("ERROR REAL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
