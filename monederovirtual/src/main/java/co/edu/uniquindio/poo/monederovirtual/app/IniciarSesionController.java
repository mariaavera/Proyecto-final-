package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BaseInformaciónCliente;
import model.Cliente;

public class IniciarSesionController {
    @FXML
    private TextField txtCedula;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnIngresar;

    @FXML
    private void iniciarSesion() {
        String cedula = txtCedula.getText();
        String pass = txtContrasena.getText();
        if (cedula.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Completa todos los campos.");
            return;
        }
        Cliente cliente = BaseInformaciónCliente.validarLogin(cedula, pass);

        if (cliente == null) {
            lblMensaje.setText("Cédula o contraseña incorrecta.");
            return;
        }
        abrirMenu(cliente);
        ((Stage) btnIngresar.getScene().getWindow()).close();
    }

    private void abrirMenu(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            MenuPrincipalController menuController = loader.getController();
            menuController.setCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle("Menú Principal");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

