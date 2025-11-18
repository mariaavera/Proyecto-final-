package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Cliente;

public class VistaPrincipalController {
    private Cliente cliente;

    @FXML
    private Button btnSalir;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void abrirVentanaConCliente(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof ClienteControlador cc) {
                cc.setCliente(cliente);
            }

            Stage stage = (Stage) btnSalir.getScene().getWindow();
            Scene nuevaScene = new Scene(root);
            stage.setScene(nuevaScene);
            stage.sizeToScene();
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando ventana: " + e.getMessage());
        }
    }

    @FXML
    private void DepositarDineroaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/depositarDinero.fxml");
    }

    @FXML
    private void RetirarDineroaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/retirarDinero.fxml");
    }

    @FXML
    private void TransferirDineroaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/transferencia.fxml");
    }

    @FXML
    private void SistemaPuntosaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/SistemaPuntos.fxml");
    }

    @FXML
    private void AnalizarGastosaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/AnalizarGastos.fxml");
    }

    @FXML
    private void ConsultaSaldoaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/ConsultaSaldo.fxml");
    }

    @FXML
    private void ConsultaHistorialaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/ConsultarHistorial.fxml");
    }

    @FXML
    private void ProgramarTransaccionaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/ProgramarTransaccion.fxml");
    }

    @FXML
    private void CrearCuentaaction() {
        abrirVentanaConCliente("/co/edu/uniquindio/poo/monederovirtual/CrearCuenta.fxml");
    }

    @FXML
    private void Saliraction() {
        ((Stage) btnSalir.getScene().getWindow()).close();
    }
}