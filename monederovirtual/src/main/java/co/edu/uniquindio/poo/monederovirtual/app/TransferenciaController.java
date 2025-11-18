package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BaseInformacionCliente;
import model.Cliente;
import model.Cuenta;

import javafx.scene.control.Label;

public class TransferenciaController implements ClienteControlador{

    @FXML private ComboBox<String> txtCuentaOrigen;
    @FXML private TextField txtValorATransferir;
    @FXML private TextField txtCuentaDestinatario;
    @FXML private Label lblMensaje;

    private Cliente cliente;
    private Cuenta cuentaOrigen;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        txtCuentaOrigen.getItems().clear();

        if (cliente == null || cliente.getCuentas().isEmpty()) {
            lblMensaje.setText("No tienes cuentas registradas.");
            return;
        }

        for (Cuenta cuenta : cliente.getCuentas()) {
            txtCuentaOrigen.getItems().add(cuenta.getId());
        }

        txtCuentaOrigen.getSelectionModel().selectFirst();
        cuentaOrigen = cliente.getCuentas().getFirst();
    }

    @FXML
    public void TransferirDineroaction() {
        try {
            String numeroOrigen = txtCuentaOrigen.getValue();
            if (numeroOrigen == null) {
                lblMensaje.setText("Seleccione la cuenta origen.");
                return;
            }

            cuentaOrigen = BaseInformacionCliente.buscarCuentaPorNumero(numeroOrigen);
            if (cuentaOrigen == null) {
                lblMensaje.setText("La cuenta origen no existe.");
                return;
            }

            double monto = Double.parseDouble(txtValorATransferir.getText().trim().replace(",", "."));

            String numeroDestino = txtCuentaDestinatario.getText().trim();
            Cuenta cuentaDestino = BaseInformacionCliente.buscarCuentaPorNumero(numeroDestino);

            if (cuentaDestino == null) {
                lblMensaje.setText("La cuenta destino no existe.");
                return;
            }

            String resultado = cuentaOrigen.transferirDinero(cuentaDestino, monto);
            lblMensaje.setText(resultado);

        } catch (NumberFormatException nfe) {
            lblMensaje.setText("Monto inválido.");
        } catch (Exception ex) {
            lblMensaje.setText("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void Volveraction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            VistaPrincipalController menu = loader.getController();
            menu.setCliente(cliente);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            lblMensaje.setText("Error al regresar.");
            e.printStackTrace();
        }
    }
}
