package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Cliente;
import model.Cuenta;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RetirarDineroController {
    private Cliente cliente;
    private Cuenta cuentaActiva;

    @FXML
    private ComboBox<Cuenta> comboCuentas;   // nuevo

    @FXML
    private TextField txtRetirarDinero;

    @FXML
    private Label lblMensaje;

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        ObservableList<Cuenta> cuentas = FXCollections.observableArrayList(cliente.getCuentas());
        comboCuentas.setItems(cuentas);
        comboCuentas.setConverter(new StringConverter<Cuenta>() {
            @Override
            public String toString(Cuenta cuenta) {
                if (cuenta == null) return "Sin cuentas";
                return cuenta.getId();
            }

            @Override
            public Cuenta fromString(String string) {
                return null;
            }
        });

        if (!cuentas.isEmpty()) {
            comboCuentas.getSelectionModel().selectFirst();
            cuentaActiva = comboCuentas.getValue();
        } else {
            cuentaActiva = null;
            lblMensaje.setText("No tienes cuentas registradas. Crea una cuenta primero.");
        }
        comboCuentas.setOnAction(ev -> {
            cuentaActiva = comboCuentas.getValue();
            if (cuentaActiva != null) {
                lblMensaje.setText("Cuenta seleccionada: " + cuentaActiva.getId());
            }
        });
    }

    @FXML
    public void RetirarDineroaction() {
        if (cuentaActiva == null) {
            lblMensaje.setText("Selecciona una cuenta primero.");
            return;
        }

        try {
            double monto = Double.parseDouble(txtRetirarDinero.getText());
            String resultado = cuentaActiva.retirarDinero(monto);
            lblMensaje.setText(resultado);

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingrese un número válido.");
        } catch (Exception e) {
            lblMensaje.setText("Error al procesar la operación.");
            e.printStackTrace();
        }
    }

    @FXML
    private void Volveraction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            MenuPrincipalController menuController = loader.getController();
            menuController.setCliente(cliente);
            Stage stage = new Stage();
            stage.setTitle("Menú Principal");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) txtRetirarDinero.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

