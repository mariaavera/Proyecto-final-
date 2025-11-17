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

public class TransferenciaController {

    @FXML private ComboBox<String> txtCuentaOrigen;
    @FXML private TextField txtValorATransferir;
    @FXML private TextField txtCuentaDestinatario;
    @FXML private Label lblMensaje;

    private Cuenta cuentaOrigen;

    public void inicializarConCliente(Cliente cliente){
        txtCuentaOrigen.getItems().clear();
        for(Cuenta c : cliente.getCuentas()){
            txtCuentaOrigen.getItems().add(c.getId());
        }
        if(!txtCuentaOrigen.getItems().isEmpty()){
            txtCuentaOrigen.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void TransferirDineroaction() {
        try {
            String numeroOrigen = txtCuentaOrigen.getValue(); // asumo ComboBox<String>
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

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR retornando al menú: " + e.getMessage());
        }
    }
}
