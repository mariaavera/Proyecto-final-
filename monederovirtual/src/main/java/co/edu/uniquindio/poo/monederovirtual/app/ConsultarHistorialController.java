package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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


    @FXML
    public void initialize() {

        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha().toString()));

        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        colMonto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getValor()));

        colPuntos.setCellValueFactory(cellData -> {

            Transaccion t = cellData.getValue();

            try {
                var metodo = t.getClass().getMethod("getPuntosGanados");
                Object puntos = metodo.invoke(t);
                return new SimpleObjectProperty<>((Integer) puntos);

            } catch (Exception e) {
                return new SimpleObjectProperty<>(0);
            }
        });

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