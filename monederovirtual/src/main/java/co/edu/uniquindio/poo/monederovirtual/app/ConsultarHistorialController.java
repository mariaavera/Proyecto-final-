package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Cuenta;
import model.Transaccion;

public class ConsultarHistorialController {

    private Cliente clienteActivo;

    @FXML
    private TableView<Transaccion> tblTransacciones;

    @FXML
    private TableColumn<Transaccion, String> colFecha;

    @FXML
    private TableColumn<Transaccion, String> colTipo;

    @FXML
    private TableColumn<Transaccion, Double> colMonto;

    @FXML
    private TableColumn<Transaccion, Integer> colPuntos;

    @FXML
    private ComboBox<Cuenta> cbCuentaHistorial;
    public void setCliente(Cliente clienteActivo) {
        this.clienteActivo = clienteActivo;
        inicializarDatos(clienteActivo);
    }

    public void inicializarDatos(Cliente cliente) {
        this.clienteActivo = cliente;
        cbCuentaHistorial.getItems().addAll(cliente.getCuentas());
        if (!cliente.getCuentas().isEmpty()) {
            cbCuentaHistorial.setValue(cliente.getCuentas().get(0));
            cargarTransacciones(cliente.getCuentas().get(0));
        }
        cbCuentaHistorial.setOnAction(e -> {
            Cuenta seleccionada = cbCuentaHistorial.getValue();
            if (seleccionada != null) {
                cargarTransacciones(seleccionada);
            }
        });
    }

    private void cargarTransacciones(Cuenta cuenta) {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntosGanados"));

        ObservableList<Transaccion> lista =
                FXCollections.observableArrayList(cuenta.consultaTransacciones());
        tblTransacciones.setItems(lista);
    }

    @FXML
    public void cerrarVentana() {
        tblTransacciones.getScene().getWindow().hide();
    }
}

