package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import model.GenerarCuentas;

public class CrearCuentaController {
    @FXML
    private TextField txtSaldo;

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void CrearCuentaaction() {
        if (cliente == null) return;

        double saldoInicial;
        try {
            saldoInicial = Double.parseDouble(txtSaldo.getText());
        } catch (Exception e) {
            mostrarMensaje("Saldo inválido");
            return;
        }

        Cuenta nueva = new Cuenta(
                saldoInicial,
                GenerarCuentas.generarNumero(),
                cliente
        );

        cliente.getCuentas().add(nueva);

        mostrarMensaje("Cuenta creada exitosamente.\nNúmero: " + nueva.getId());
        txtSaldo.clear();
    }

    private void mostrarMensaje(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.show();
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

