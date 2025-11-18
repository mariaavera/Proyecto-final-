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

public class RetirarDineroController implements ClienteControlador{

    private Cliente cliente;
    private Cuenta cuentaActiva;

    @FXML
    private ComboBox<Cuenta> comboCuentas;

    @FXML
    private TextField txtRetirarDinero;

    @FXML
    private Label lblMensaje;

    @Override
    public void setCliente(Cliente cliente){
        this.cliente = cliente;

        if (cliente == null || cliente.getCuentas().isEmpty()) {
            lblMensaje.setText("No tienes cuentas registradas.");
            return;
        }

        comboCuentas.getItems().setAll(cliente.getCuentas());

        comboCuentas.setConverter(new StringConverter<Cuenta>() {
            @Override
            public String toString(Cuenta cuenta) {
                return (cuenta == null) ? "" : cuenta.getId();
            }

            @Override
            public Cuenta fromString(String string) {
                return null;
            }
        });

        comboCuentas.getSelectionModel().selectFirst();
        cuentaActiva = comboCuentas.getValue();

        comboCuentas.setOnAction(ev -> cuentaActiva = comboCuentas.getValue());
    }

    @FXML
    public void RetirarDineroaction() {
        if (cuentaActiva == null) {
            lblMensaje.setText("Seleccione una cuenta.");
            return;
        }

        try {
            double monto = Double.parseDouble(txtRetirarDinero.getText());
            String resultado = cuentaActiva.retirarDinero(monto);
            lblMensaje.setText(resultado);

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingrese un número válido.");
        }
    }

    @FXML
    private void Volveraction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );

            Parent root = loader.load();
            VistaPrincipalController menuController = loader.getController();
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


