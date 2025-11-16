package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.time.LocalDate;

public class TransaccionProgramadaController {
    private MonederoVirtual monederoActivo;
    private Cliente clienteActivo;

    @FXML
    private ChoiceBox<String> cbTipoTransaccion;

    @FXML
    private ComboBox<Cuenta> cbCuentaOrigen;

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
    private Cliente cliente;

    public void setCliente(MonederoVirtual monedero, Cliente cliente) {
        this.monederoActivo = monedero;
        this.clienteActivo = cliente;
        inicializarDatos(); // ahora sí se puede llamar SIN parámetros
    }

    public void inicializarDatos() {
        cbTipoTransaccion.getItems().addAll("Depósito", "Transferencia");
        cbCuentaOrigen.getItems().addAll(cliente.getCuentas());
    }

    @FXML
    public void Programaraction() {
        try {
            String tipo = cbTipoTransaccion.getValue();
            if (tipo == null) {
                lblMensaje.setText("Selecciona un tipo de transacción.");
                return;
            }

            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            if (cuentaOrigen == null) {
                lblMensaje.setText("Selecciona una cuenta de origen.");
                return;
            }

            double monto = Double.parseDouble(txtMonto.getText());
            String concepto = txtConcepto.getText();
            LocalDate fecha = dpFecha.getValue();

            if (concepto.isEmpty() || fecha == null) {
                lblMensaje.setText("Completa todos los campos.");
                return;
            }

            Transaccion transaccion;

            if (tipo.equals("Depósito")) {
                transaccion = new Deposito(monto, clienteActivo, concepto, cuentaOrigen);

            } else {
                String idDestino = txtCuentaDestino.getText();

                Cuenta cuentaDestino = BaseInformaciónCliente.buscarCuentaPorNumero(idDestino);
                if (cuentaDestino == null) {
                    lblMensaje.setText("La cuenta destino no existe.");
                    return;
                }

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