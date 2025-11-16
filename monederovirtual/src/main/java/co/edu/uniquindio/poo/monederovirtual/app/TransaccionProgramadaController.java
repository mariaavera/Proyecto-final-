package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.*;

import java.time.LocalDate;

public class TransaccionProgramadaController {
    private MonederoVirtual monederoActivo;  // Puede ser individual o multi
    private Cliente clienteActivo;

    @FXML
    private ChoiceBox<String> cbTipoTransaccion;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtConcepto;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private Label lblMensaje;

    public void inicializarDatos(MonederoVirtual monedero, Cliente cliente) {
        this.monederoActivo = monedero;
        this.clienteActivo = cliente;

        cbTipoTransaccion.getItems().addAll("Depósito", "Transferencia");
    }

    @FXML
    public void Programaraction() {

        try {
            String tipo = cbTipoTransaccion.getValue();
            if (tipo == null) {
                lblMensaje.setText("Selecciona un tipo de transacción.");
                return;
            }

            double monto = Double.parseDouble(txtMonto.getText());
            String concepto = txtConcepto.getText();

            if (concepto == null || concepto.isEmpty()) {
                lblMensaje.setText("Debes ingresar un concepto.");
                return;
            }

            LocalDate fecha = dpFecha.getValue();
            if (fecha == null) {
                lblMensaje.setText("Selecciona una fecha.");
                return;
            }
            Transaccion transaccion;
            if (tipo.equals("Depósito")) {

                Cuenta cuentaDestino = clienteActivo.getCuenta();

                transaccion = new Deposito(monto, clienteActivo, concepto, cuentaDestino);
            } else {

                String idDestino = txtCuentaDestino.getText();

                if (idDestino == null || idDestino.isEmpty()) {
                    lblMensaje.setText("Debes ingresar el ID de la cuenta destino.");
                    return;
                }
                Cuenta cuentaDestino = null;

                if (cuentaDestino == null) {
                    lblMensaje.setText("La cuenta destino no existe (implementa buscarCuentaPorId).");
                    return;
                }

                Cuenta cuentaOrigen = clienteActivo.getCuenta();

                transaccion = new Transferencia(monto, clienteActivo, concepto, cuentaOrigen, cuentaDestino);
            }
            monederoActivo.programarTransaccion(transaccion, fecha);

            lblMensaje.setText("Transacción programada correctamente.");

        } catch (NumberFormatException e) {
            lblMensaje.setText("El monto no es válido.");
        }
    }

    @FXML
    public void Cerraraction() {
        lblMensaje.getScene().getWindow().hide();
    }
}