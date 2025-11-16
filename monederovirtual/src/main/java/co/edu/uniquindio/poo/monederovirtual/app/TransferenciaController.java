package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import model.Cuenta;

import java.awt.*;

public class TransferenciaController {
    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private Label lblMensaje;

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public void inicializarDatos(Cuenta origen, Cuenta destino){
        this.cuentaOrigen = origen;
        this.cuentaDestino = destino;
    }

    @FXML
    public void transferir(){
        try{
            double monto = Double.parseDouble(txtMonto.getText());

            if(cuentaOrigen.transferirDinero(cuentaDestino, monto)){
                lblMensaje.setText("Transferencia exitosa.");
            } else {
                lblMensaje.setText("Error al transferir.");
            }

        } catch(Exception e){
            lblMensaje.setText("Monto inválido.");
        }
    }
}

