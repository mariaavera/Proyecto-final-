package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.MonederoVirtual;

public class DepositarDineroController {

    @FXML
    private TextField txtMonto;

    @FXML
    private Label lblMensaje;

    private Cuenta cuentaActiva;

    public void inicializarDatos(Cuenta cuenta) {
        this.cuentaActiva = cuenta;
    }
    @FXML
    public void DepositarDineroaction() {
        try {
            double valor = Double.parseDouble(txtMonto.getText());

            boolean exito = cuentaActiva.depositarDinero(valor);

            if (exito) {
                lblMensaje.setText("Depósito exitoso.");
            } else {
                lblMensaje.setText("No se pudo realizar el depósito.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingrese un número válido.");
        }
    }
}
