package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RetirarDineroController {
    private Cliente cliente;
    private Cuenta cuentaActiva;
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.cuentaActiva = cliente.getCuenta();
    }
    @FXML
    private TextField txtRetirarDinero;

    @FXML
    private Label lblMensaje;

    @FXML
    public void RetirarDineroaction() {
        try {
            double monto = Double.parseDouble(txtRetirarDinero.getText());
            String resultado = cuentaActiva.retirarDinero(monto);
            lblMensaje.setText(resultado);

        } catch (Exception e) {
            lblMensaje.setText("Ingrese un número válido.");
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
            ((Stage) txtRetirarDinero.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
