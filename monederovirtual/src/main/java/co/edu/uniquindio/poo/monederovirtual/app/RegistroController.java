package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import model.MonederoVirtual;

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
    private void registrarCliente() {

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

        Cliente cliente = new Cliente(nombre, cedula, pass);

        Cuenta cuenta = new Cuenta(0, generarNumeroCuenta());
        cliente.setCuenta(cuenta);

        cliente.agregarMonedero(new MonederoVirtual("Principal"));
        cliente.agregarMonedero(new MonederoVirtual("Ahorros"));
        cliente.agregarMonedero(new MonederoVirtual("Gastos"));

        BaseDatosClientes.agregarCliente(cliente);

        lblMensaje.setText("Registro exitoso. Ahora inicia sesión.");

        cerrarVentana();
    }

    private String generarNumeroCuenta() {
        return "CU" + (int)(Math.random() * 900000 + 100000);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
        stage.close();
    }
}
