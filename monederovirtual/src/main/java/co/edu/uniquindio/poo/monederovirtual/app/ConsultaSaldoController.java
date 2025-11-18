package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;

public class ConsultaSaldoController implements ClienteControlador {

    @FXML
    private Label lblSaldo;

    @FXML
    private ComboBox<Cuenta> cbCuenta;

    private Cuenta cuenta;
    private Cliente cliente;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;


        if (cliente != null && cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            cbCuenta.getItems().clear();
            cbCuenta.getItems().addAll(cliente.getCuentas());
            cbCuenta.getSelectionModel().selectFirst();
            this.cuenta = cbCuenta.getValue();
        } else {
            this.cuenta = null;
        }

        inicializarDatos();

        cbCuenta.setOnAction(event -> {
            cuenta = cbCuenta.getValue();
            inicializarDatos();
        });
    }

    public void inicializarDatos() {
        if (cuenta != null) {
            lblSaldo.setText("Saldo actual: " + cuenta.getSaldo());
        } else {
            lblSaldo.setText("No hay cuenta disponible");
        }
    }

    @FXML
    void Volveraction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            VistaPrincipalController controller = loader.getController();
            controller.setCliente(cliente);

            Stage stage = (Stage) lblSaldo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al volver al menú principal");
        }
    }
}


