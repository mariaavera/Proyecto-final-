package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BaseInformacionCliente;
import model.Cliente;

public class IniciarSesionController {
        @FXML
        private TextField txtUsuario;

        @FXML
        private PasswordField txtContrasena;

    @FXML
    private void IniciarSesionaction() {

        String usuario = txtUsuario.getText();
        String clave = txtContrasena.getText();

        Cliente cliente = BaseInformacionCliente.validarLogin(usuario, clave);

        if (cliente != null) {
            abrirMenu(cliente);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void abrirMenu(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml"));
            Parent root = loader.load();

            VistaPrincipalController controller = loader.getController();
            controller.setCliente(cliente);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

            ((Stage) txtUsuario.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }