package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class SistemaPuntosController implements ClienteControlador{

    @FXML
    private Label lblPuntos;

    @FXML
    private Label lblRango;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtPuntosACanjear;

    private Cliente cliente;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente == null) {
            lblMensaje.setText("Error: cliente no asignado.");
            return;
        }

        actualizarVista();
    }

    private void actualizarVista() {
        lblPuntos.setText("Puntos: " + cliente.getPuntos());
        lblRango.setText("Rango: " + calcularRango(cliente.getPuntos()));
    }

    private String calcularRango(int puntos) {
        if (puntos <= 500) return "Bronce";
        if (puntos <= 1000) return "Plata";
        if (puntos <= 5000) return "Oro";
        return "Platino";
    }

    @FXML
    public void Canjearaction() {
        try {
            int cantidad = Integer.parseInt(txtPuntosACanjear.getText());

            if (cantidad <= 0) {
                lblMensaje.setText("Ingresa una cantidad válida.");
                return;
            }

            if (cliente.getPuntos() >= cantidad) {

                cliente.descontarPuntos(cantidad);

                lblMensaje.setText("Canje exitoso. Te quedan: "
                        + cliente.getPuntos() + " puntos.");

                actualizarVista();

            } else {
                lblMensaje.setText("No tienes suficientes puntos.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingresa un número válido.");
        }
    }

    @FXML
    public void Volveraction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
            );
            Parent root = loader.load();

            VistaPrincipalController menu = loader.getController();
            menu.setCliente(cliente);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            lblMensaje.setText("Error regresando al menú.");
            e.printStackTrace();
        }
    }
}