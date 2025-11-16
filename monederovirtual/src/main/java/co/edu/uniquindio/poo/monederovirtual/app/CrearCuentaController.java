package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
}

