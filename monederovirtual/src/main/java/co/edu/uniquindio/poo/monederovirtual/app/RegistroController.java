package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

public class RegistroController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private PasswordField txtConfirmar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblMensaje;

    @FXML
    private void Registraraction() {

        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String pass = txtContrasena.getText();
        String pass2 = txtConfirmar.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Completa todos los campos.");
            return;
        }

        if (!pass.equals(pass2)) {
            lblMensaje.setText("Las contraseñas no coinciden.");
            return;
        }
        Cliente cliente = new Cliente(nombre, cedula, pass, 0);

        Cuenta cuenta = new Cuenta(
                0,
                generarNumeroCuenta(),
                cliente
        );

        cliente.getCuentas().add(cuenta);

        MonederoIndividual principal = new MonederoIndividual("Principal", 0);
        MonederoIndividual ahorros   = new MonederoIndividual("Ahorros", 0);
        MonederoIndividual gastos    = new MonederoIndividual("Gastos", 0);
        Multimonedero multimonedero = new Multimonedero("Multimonedero", 0);

        multimonedero.agregarMonedero(principal);
        multimonedero.agregarMonedero(ahorros);
        multimonedero.agregarMonedero(gastos);

        cliente.registrarMonedero(principal);
        cliente.registrarMonedero(ahorros);
        cliente.registrarMonedero(gastos);
        cliente.registrarMonedero(multimonedero);
        BaseInformacionCliente.agregarCliente(cliente);

        lblMensaje.setText("Registro exitoso. Ahora inicia sesión.");

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event -> cerrarVentana());
        pause.play();
    }

    private String generarNumeroCuenta() {
        return "CU" + (int)(Math.random() * 900000 + 100000);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
        stage.close();
    }

}