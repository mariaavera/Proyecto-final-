package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import model.Cuenta;
import java.awt.*;

public class RetirarDineroController {
    @FXML
    private TextField txtMonto;

    @FXML
    private Label lblMensaje;

    private Cuenta cuentaActiva;

    public void inicializarDatos(Cuenta cuenta){
        this.cuentaActiva = cuenta;
    }

    @FXML
    public void retirar(){
        try{
            double monto = Double.parseDouble(txtMonto.getText());

            if(cuentaActiva.retirarDinero(monto)){
                lblMensaje.setText("Retiro exitoso. Monto: " + monto);
            } else {
                lblMensaje.setText("Saldo insuficiente o valor inválido.");
            }
        }catch(Exception e){
            lblMensaje.setText("Ingrese un número válido.");
        }
    }
}
