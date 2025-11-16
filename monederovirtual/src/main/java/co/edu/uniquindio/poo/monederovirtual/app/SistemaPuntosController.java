package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cliente;

public class SistemaPuntosController {
    private Cliente clienteActivo;

    @FXML
    private Label lblPuntos;

    @FXML
    private Label lblRango;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtPuntosACanjear;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        inicializarDatos(cliente);
    }

    public void inicializarDatos(Cliente cliente) {
        this.clienteActivo = cliente;
        actualizarVista();
    }

    private void actualizarVista() {
        lblPuntos.setText("Puntos: " + clienteActivo.getPuntos());
        lblRango.setText("Rango: " + calcularRango(clienteActivo.getPuntos()));
    }

    private String calcularRango(int puntos) {
        if (puntos <= 500) return "Bronce";
        if (puntos <= 1000) return "Plata";
        if (puntos <= 5000) return "Oro";
        return "Platino";
    }

    @FXML
    public void canjearPuntos() {
        try {
            int cantidad = Integer.parseInt(txtPuntosACanjear.getText());

            if (cantidad <= 0) {
                lblMensaje.setText("Ingresa una cantidad válida.");
                return;
            }

            if (clienteActivo.getPuntos() >= cantidad) {

                clienteActivo.descontarPuntos(cantidad);

                lblMensaje.setText("Canje exitoso. Te quedan: "
                        + clienteActivo.getPuntos() + " puntos.");

                actualizarVista();  // <-- actualiza puntos y rango

            } else {
                lblMensaje.setText("No tienes suficientes puntos.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingresa un número válido.");
        }
    }
}