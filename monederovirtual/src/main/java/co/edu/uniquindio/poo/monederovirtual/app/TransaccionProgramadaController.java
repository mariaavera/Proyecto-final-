package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;

import static model.BaseInformacionCliente.getMonederoActual;

public class TransaccionProgramadaController {
    @FXML private ChoiceBox<String> cbTipoTransaccion;
    @FXML private ComboBox<Cuenta> cbCuentaOrigen;
    @FXML private TextField txtMonto;
    @FXML private TextField txtConcepto;
    @FXML private TextField txtCantidadDias;
    @FXML private ComboBox<Cuenta> cbCuentaDestino;
    @FXML private DatePicker dpFechaInicio;
    @FXML private Button btnProgramar;
    @FXML private Button btnCerrar;

    private Cliente cliente;
    public void inicializarConCliente(Cliente cliente) {
        this.cliente = cliente;

        cbCuentaOrigen.getItems().clear();

        cbCuentaOrigen.getItems().addAll(cliente.getCuentas());

        if (!cbCuentaOrigen.getItems().isEmpty()) {
            cbCuentaOrigen.getSelectionModel().selectFirst();
        }
        cbTipoTransaccion.getItems().clear();
        cbTipoTransaccion.getItems().addAll("Depósito", "Transferencia");
    }
    public void Programaraction() {

        try {

            String tipo = cbTipoTransaccion.getValue();
            double valor = Double.parseDouble(txtMonto.getText());
            String concepto = txtConcepto.getText();
            LocalDate fecha = dpFechaInicio.getValue();

            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            Cuenta cuentaDestino = cbCuentaDestino.getValue();


            MonederoVirtual monedero = BaseInformacionCliente.getMonederoActual();

            if (monedero == null) {
                mostrarMensaje("No se encontró un monedero activo.");
                return;
            }

            Cliente cliente = monedero.getListaClientes().get(0);

            Transaccion trans = null;

            switch (tipo) {

                case "Depósito":
                    if (cuentaDestino == null) {
                        mostrarMensaje("Seleccione una cuenta destino.");
                        return;
                    }
                    trans = new Deposito(valor, cliente, concepto, cuentaDestino);
                    break;

                case "Transferencia":
                    if (cuentaOrigen == null || cuentaDestino == null) {
                        mostrarMensaje("Seleccione cuentas origen y destino.");
                        return;
                    }
                    trans = new Transferencia(valor, cliente, concepto, cuentaOrigen, cuentaDestino);
                    break;

                default:
                    mostrarMensaje("Solo se pueden programar depósitos y transferencias.");
                    return;
            }
            monedero.programarTransaccion(trans, fecha);

            mostrarMensaje("Transacción programada correctamente.");

        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void mostrarMensaje(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensaje");
        alert.setContentText(msg);
        alert.show();
    }
    @FXML
    public void Cerraraction(ActionEvent event) {
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