package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button btnRegistro;

    @FXML
    private Button btnLogin;

    @FXML
    private void Registrarseaction() {
        abrirVentana("/co/edu/uniquindio/poo/monederovirtual/Registro.fxml",
                "Crear cuenta");
    }

    @FXML
    private void IniciarSesionaction() {
        abrirVentana("/co/edu/uniquindio/poo/monederovirtual/IniciarSesion.fxml",
                "Iniciar Sesión");
    }

    private void abrirVentana(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo cargar " + ruta);
        }
    }
}