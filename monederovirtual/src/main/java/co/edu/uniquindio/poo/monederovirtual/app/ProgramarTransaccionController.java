package co.edu.uniquindio.poo.monederovirtual.app;

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

public class ProgramarTransaccionController implements ClienteControlador{


    @FXML private ChoiceBox<String> cbTipoTransaccion;
    @FXML private ComboBox<Cuenta> cbCuentaOrigen;
    @FXML private ComboBox<Cuenta> cbCuentaDestino;

    @FXML private TextField txtMonto;
    @FXML private TextField txtConcepto;
    @FXML private TextField txtCantidadDias;

    @FXML private DatePicker dpFechaInicio;

    @FXML private Button btnProgramar;
    @FXML private Button btnCerrar;

    private Cliente cliente;
    private MonederoVirtual monedero;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        inicializarConCliente(cliente);
    }

    private void inicializarConCliente(Cliente cliente) {

        this.monedero = BaseInformacionCliente.getMonederoActual();

        if (cliente == null || cliente.getCuentas() == null) {
            mostrarMensaje("Error cargando cuentas del cliente.");
            return;
        }

        cbCuentaOrigen.getItems().setAll(cliente.getCuentas());
        cbCuentaDestino.getItems().setAll(cliente.getCuentas());

        if (!cliente.getCuentas().isEmpty()) {
            cbCuentaOrigen.getSelectionModel().select(0);
        }
        cbTipoTransaccion.getItems().setAll("Depósito", "Transferencia");
    }

    @FXML
    public void Programaraction() {

        try {
            if (cbTipoTransaccion.getValue() == null) {
                mostrarMensaje("Seleccione el tipo de transacción.");
                return;
            }

            if (dpFechaInicio.getValue() == null) {
                mostrarMensaje("Seleccione la fecha de ejecución.");
                return;
            }

            double valor = Double.parseDouble(txtMonto.getText());
            if (valor <= 0) {
                mostrarMensaje("El monto debe ser mayor a 0.");
                return;
            }

            String concepto = txtConcepto.getText().trim();
            if (concepto.isEmpty()) {
                mostrarMensaje("Ingrese un concepto.");
                return;
            }

            String tipo = cbTipoTransaccion.getValue();
            LocalDate fecha = dpFechaInicio.getValue();

            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            Cuenta cuentaDestino = cbCuentaDestino.getValue();

            Transaccion trans;

            switch (tipo) {

                case "Depósito":
                    if (cuentaDestino == null) {
                        mostrarMensaje("Seleccione cuenta destino.");
                        return;
                    }
                    trans = new Deposito(valor, cliente, concepto, cuentaDestino);
                    break;

                case "Transferencia":
                    if (cuentaOrigen == null || cuentaDestino == null) {
                        mostrarMensaje("Seleccione origen y destino.");
                        return;
                    }
                    trans = new Transferencia(valor, cliente, concepto, cuentaOrigen, cuentaDestino);
                    break;

                default:
                    mostrarMensaje("Tipo de transacción desconocido.");
                    return;
            }

            monedero.programarTransaccion(trans, fecha);

            mostrarMensaje("Transacción programada exitosamente.");

        } catch (NumberFormatException e) {
            mostrarMensaje("Monto inválido. Ingrese un número.");
        }
    }

    @FXML
    public void Cerraraction(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );
            Parent root = loader.load();

            VistaPrincipalController controller = loader.getController();
            controller.setCliente(cliente);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR retornando al menú: " + e.getMessage());
        }
    }
    private void mostrarMensaje(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensaje");
        alert.setContentText(msg);
        alert.show();
    }
}