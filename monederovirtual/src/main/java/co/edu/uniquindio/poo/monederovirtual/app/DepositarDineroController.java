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

public class DepositarDineroController implements ClienteControlador{

    @FXML private TextField txtDepositoDinero;
    @FXML private Label lblMensaje;
    @FXML private ComboBox<Cuenta> cbCuentaDeposito;

    private Cliente cliente;
    private Cuenta cuentaActiva;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente == null || cliente.getCuentas() == null || cliente.getCuentas().isEmpty()) {
            System.out.println("ERROR: Cliente no tiene cuentas.");
            return;
        }

        this.cuentaActiva = cliente.getCuentas().getFirst();
        cbCuentaDeposito.getItems().setAll(cliente.getCuentas());
        cbCuentaDeposito.setValue(cuentaActiva);
    }

    @FXML
    private void DepositarDineroaction() {
        try {
            double monto = Double.parseDouble(txtDepositoDinero.getText());

            if (cuentaActiva != null) {
                String mensaje = cuentaActiva.depositarDinero(monto);
                lblMensaje.setText(mensaje);
            } else {
                lblMensaje.setText("Seleccione una cuenta.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingrese un valor válido.");
        }
    }

    @FXML
    private void Volveraction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            VistaPrincipalController menuController = loader.getController();
            menuController.setCliente(cliente);

            Stage stageActual = (Stage) txtDepositoDinero.getScene().getWindow();

            stageActual.setScene(new Scene(root));
            stageActual.setTitle("Menú Principal");
            stageActual.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}