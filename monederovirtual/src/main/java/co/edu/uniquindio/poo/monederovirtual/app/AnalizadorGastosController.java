package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AnalizadorGastos;
import model.Cliente;
import model.Transaccion;

import java.time.LocalDate;
import java.util.List;

public class AnalizadorGastosController implements ClienteControlador {

    @FXML private Button btnActualizar;
    @FXML private TableColumn<Transaccion, String> colCategoria;
    @FXML private TableColumn<Transaccion, String> colConcepto;
    @FXML private TableColumn<Transaccion, LocalDate> colFecha;
    @FXML private TableColumn<Transaccion, Double> colMonto;
    @FXML private ComboBox<String> comboCategoria;
    @FXML private ComboBox<String> comboPeriodo;
    @FXML private ComboBox<String> cbCuenta;
    @FXML private Label lblPromedio;
    @FXML private Label lblTotal;
    @FXML private PieChart pieChartGastos;
    @FXML private TableView<Transaccion> tableGastos;
    @FXML private Label lblAlerta;

    private Cliente cliente;
    private AnalizadorGastos analizador;
    private boolean vistaInicializada = false;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.analizador = new AnalizadorGastos(cliente);

        if (!vistaInicializada) {
            inicializarVista();
            vistaInicializada = true;
        }

        cargarCuentas();
        actualizarVista();
    }

    private void inicializarVista() {

        comboPeriodo.getItems().addAll(
                "Enero","Febrero","Marzo","Abril","Mayo","Junio",
                "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
        );

        comboCategoria.getItems().addAll("Depósito","Retiro","Transferencia");

        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));

        colCategoria.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getClass().getSimpleName()));

        colConcepto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getConcepto()));

        colMonto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValor()));

        btnActualizar.setOnAction(e -> actualizarVista());
    }

    private void cargarCuentas() {
        cbCuenta.getItems().clear();
        if (cliente != null && cliente.getCuentas() != null) {
            cliente.getCuentas().forEach(c ->
                    cbCuenta.getItems().add(c.getId())   // muestra número de cuenta
            );
        }
    }

    private void actualizarVista() {
        if (cliente == null || analizador == null) return;

        Integer mes = comboPeriodo.getSelectionModel().getSelectedIndex() + 1;
        String categoriaSeleccionada = comboCategoria.getValue();

        String cuentaSeleccionada = cbCuenta.getValue();

        if (cuentaSeleccionada == null) {
            tableGastos.getItems().clear();
            pieChartGastos.getData().clear();
            lblTotal.setText("Total: 0");
            lblPromedio.setText("Promedio mensual: 0");
            return;
        }

        List<Transaccion> lista = cliente.getCuentas()
                .stream()
                .filter(c -> c.getId().equals(cuentaSeleccionada))
                .flatMap(c -> c.consultaTransacciones().stream())
                .toList();

        List<Transaccion> filtradas = lista.stream()
                .filter(t -> mes == 0 || t.getFecha().getMonthValue() == mes)
                .filter(t -> categoriaSeleccionada == null
                        || t.getClass().getSimpleName().equals(categoriaSeleccionada))
                .toList();

        tableGastos.setItems(FXCollections.observableArrayList(filtradas));
        pieChartGastos.getData().clear();
        double total = filtradas.stream().mapToDouble(Transaccion::getValor).sum();

        pieChartGastos.getData().add(
                new PieChart.Data(
                        categoriaSeleccionada == null ? "Todos" : categoriaSeleccionada,
                        total
                )
        );

        lblTotal.setText("Total: " + total);
        lblPromedio.setText(
                "Promedio mensual: " + analizador.calcularPromedioGastoMensual(LocalDate.now().getYear())
        );
        int año = LocalDate.now().getYear();
        String alerta = analizador.generarAlerta(mes, año);
        lblAlerta.setText(alerta);
    }

    @FXML
    public void Volveraction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );
            Parent root = loader.load();

            VistaPrincipalController menu = loader.getController();
            menu.setCliente(cliente);

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