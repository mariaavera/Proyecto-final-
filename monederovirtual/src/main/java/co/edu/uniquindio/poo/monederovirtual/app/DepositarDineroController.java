package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import model.MonederoVirtual;

public class DepositarDineroController {

    @FXML private TextField txtDepositoDinero;
    @FXML private Label lblMensaje;
    @FXML private ComboBox<Cuenta> cbCuentaDeposito;

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cbCuentaDeposito.getItems().addAll(cliente.getCuentas());
    }

    @FXML
    public void DepositarDineroaction() {
        try {
            Cuenta cuentaSeleccionada = cbCuentaDeposito.getValue();

            if (cuentaSeleccionada == null) {
                lblMensaje.setText("Selecciona una cuenta para depositar.");
                return;
            }

            double monto = Double.parseDouble(txtDepositoDinero.getText());
            String resultado = cuentaSeleccionada.depositarDinero(monto);
            lblMensaje.setText(resultado);

        } catch (NumberFormatException e) {
            lblMensaje.setText("Error: ingrese un número válido.");
        }
    }

    @FXML
    private void Volveraction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            MenuPrincipalController menuController = loader.getController();
            menuController.setCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle("Menú Principal");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) txtDepositoDinero.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
