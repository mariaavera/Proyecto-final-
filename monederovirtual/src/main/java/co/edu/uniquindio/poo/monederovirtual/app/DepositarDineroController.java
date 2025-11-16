package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        try {
            double monto = Double.parseDouble(txtDepositoDinero.getText());
            String resultado = cuentaActiva.depositarDinero(monto);
            lblMensaje.setText(resultado);
        } catch (NumberFormatException e) {
            lblMensaje.setText("Error: ingrese un número válido");
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
