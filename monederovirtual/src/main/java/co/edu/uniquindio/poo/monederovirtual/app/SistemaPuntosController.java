package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

public class SistemaPuntosController implements ClienteControlador {

    @FXML
    private Label lblPuntos;

    @FXML
    private Label lblRango;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtPuntosACanjear;

    @FXML
    private ComboBox<Cuenta> comboCuentas;

    private Cliente cliente;

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente == null) {
            lblMensaje.setText("Error: cliente no asignado.");
            return;
        }

        comboCuentas.getItems().setAll(cliente.getCuentas());

        actualizarVista();
    }

    private void actualizarVista() {
        lblPuntos.setText("Puntos: " + cliente.getPuntos());
        lblRango.setText("Rango: " + cliente.getRango());
    }

    @FXML
    public void Canjearaction() {
        try {
            int cantidad = Integer.parseInt(txtPuntosACanjear.getText());

            if (cantidad <= 0) {
                lblMensaje.setText("Ingresa una cantidad válida.");
                return;
            }
            if (cliente.getPuntos() < cantidad) {
                lblMensaje.setText("No tienes suficientes puntos.");
                return;
            }

            Cuenta cuentaSeleccionada = comboCuentas.getValue();
            if (cuentaSeleccionada == null) {
                lblMensaje.setText("Selecciona una cuenta.");
                return;
            }

            Beneficio beneficio = null;

            if (cantidad == 1000) beneficio = new BeneficioBonoSaldo();
            else if (cantidad == 500) beneficio = new BeneficioRetiro();
            else if (cantidad == 100) beneficio = new BeneficioTransferencia();
            else {
                lblMensaje.setText("No existe un beneficio para esa cantidad de puntos.");
                return;
            }

            boolean exito = cliente.canjear(beneficio);

            if (exito) {
                lblMensaje.setText(
                        "Canje exitoso: " + beneficio.getNombre() +
                                ". Te quedan " + cliente.getPuntos() + " puntos."
                );
                actualizarVista();
            } else {
                lblMensaje.setText("No se pudo realizar el canje.");
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