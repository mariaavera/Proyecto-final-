package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import model.AnalizadorGastos;
import model.Transaccion;

import java.time.LocalDate;
import java.util.List;

public class AnalizadorGastosController {

        @FXML
        private Button btnActualizar;

        @FXML
        private TableColumn<Transaccion, String> colCategoria;

        @FXML
        private TableColumn<Transaccion, String> colConcepto;

        @FXML
        private TableColumn<Transaccion, LocalDate> colFecha;

        @FXML
        private TableColumn<Transaccion, Double> colMonto;

        @FXML
        private ComboBox<String> comboCategoria;

        @FXML
        private ComboBox<String> comboPeriodo;

        @FXML
        private Label lblPromedio;

        @FXML
        private Label lblTotal;

        @FXML
        private PieChart pieChartGastos;

        @FXML
        private TableView<Transaccion> tableGastos;

        private AnalizadorGastos analizador;

        public void setAnalizadorGastos(AnalizadorGastos analizador) {
            this.analizador = analizador;
            inicializarVista();
        }

        private void inicializarVista() {

            comboPeriodo.getItems().addAll("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
            comboCategoria.getItems().addAll("Retiro","Depósito","Transferencia");

            colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));
            colCategoria.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClass().getSimpleName()));
            colConcepto.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getConcepto()));
            colMonto.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValor()));


            btnActualizar.setOnAction(e -> actualizarVista());
        }

        private void actualizarVista() {
            if (analizador == null) return;

            int mesSeleccionado = comboPeriodo.getSelectionModel().getSelectedIndex() + 1;
            String categoriaSeleccionada = comboCategoria.getValue();

            List<Transaccion> transacciones = analizador.getCuenta().consultaTransacciones();
            List<Transaccion> filtradas = transacciones.stream()
                    .filter(t -> t.getFecha().getMonthValue() == mesSeleccionado)
                    .filter(t -> categoriaSeleccionada == null || t.getClass().getSimpleName().equals(categoriaSeleccionada))
                    .toList();
            tableGastos.setItems(FXCollections.observableArrayList(filtradas));
            pieChartGastos.getData().clear();
            double total = filtradas.stream().mapToDouble(Transaccion::getValor).sum();
            pieChartGastos.getData().add(new PieChart.Data(categoriaSeleccionada != null ? categoriaSeleccionada : "Todos", total));
            lblTotal.setText("Total: " + total);
            lblPromedio.setText("Promedio mensual: " + analizador.calcularPromedioGastoMensual(LocalDate.now().getYear()));
        }
    }
