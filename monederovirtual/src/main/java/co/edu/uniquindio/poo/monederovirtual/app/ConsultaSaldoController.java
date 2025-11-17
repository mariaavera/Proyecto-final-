package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;

public class ConsultaSaldoController {
    @FXML
    private Label lblSaldo;

    private Cuenta cuenta;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuenta = cliente.getCuentas().get(0);
        }
        inicializarDatos();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml"));

            Parent root = loader.load();
            MenuPrincipalController controller = loader.getController();
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

