package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import model.Transaccion;

public class ConsultarHistorialController implements ClienteControlador{

    private Cliente clienteActivo;
    private Cuenta cuentaActiva;

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

    // Se ejecuta cuando los controles FXML YA están listos
    @FXML
    public void initialize() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntosGanados"));

        cbCuentaHistorial.setOnAction(e -> {
            Cuenta seleccionada = cbCuentaHistorial.getValue();
            if (seleccionada != null) {
                cargarTransacciones(seleccionada);
            }
        });
    }

    @Override
    public void setCliente(Cliente cliente) {
        this.clienteActivo = cliente;

        if (cliente != null && cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuentaActiva = cliente.getCuentas().get(0);
        }

        cargarCuentas();
    }

    private void cargarCuentas() {
        if (clienteActivo == null) return;

        cbCuentaHistorial.getItems().clear();
        cbCuentaHistorial.getItems().addAll(clienteActivo.getCuentas());

        if (!clienteActivo.getCuentas().isEmpty()) {
            Cuenta primera = clienteActivo.getCuentas().get(0);
            cbCuentaHistorial.setValue(primera);
            cargarTransacciones(primera);
        }
    }

    private void cargarTransacciones(Cuenta cuenta) {
        ObservableList<Transaccion> lista =
                FXCollections.observableArrayList(cuenta.consultaTransacciones());
        tblTransacciones.setItems(lista);
    }

    @FXML
    public void Cerraraction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR retornando al menú: " + e.getMessage());
        }
    }
}