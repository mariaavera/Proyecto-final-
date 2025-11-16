package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.BaseInformaciónCliente;
import model.Cliente;
import model.Cuenta;

import javafx.scene.control.Label;

public class TransferenciaController {

    @FXML private ComboBox<String> cboCuentasOrigen; // mostrará número de cuenta
    @FXML private TextField txtMonto;
    @FXML private TextField txtCuentaDestino;
    @FXML private Label lblMensaje;

    private Cuenta cuentaOrigen;

    // este método lo llamas desde el controlador que abre la vista, y le pasas el cliente actual
    public void inicializarConCliente(Cliente cliente){
        // cargar cuentas en el combo
        cboCuentasOrigen.getItems().clear();
        for(Cuenta c : cliente.getCuentas()){
            cboCuentasOrigen.getItems().add(c.getId());
        }
        if(!cboCuentasOrigen.getItems().isEmpty()){
            cboCuentasOrigen.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void TransferirDineroaction(){
        try{
            String numeroOrigen = cboCuentasOrigen.getValue();
            if(numeroOrigen == null){
                lblMensaje.setText("Seleccione la cuenta origen.");
                return;
            }
            cuentaOrigen = BaseInformaciónCliente.buscarCuentaPorNumero(numeroOrigen);
            double monto = Double.parseDouble(txtMonto.getText().trim().replace(",", "."));
            String numeroDestino = txtCuentaDestino.getText().trim();

            Cuenta cuentaDestino = BaseInformaciónCliente.buscarCuentaPorNumero(numeroDestino);
            if(cuentaDestino == null){
                lblMensaje.setText("La cuenta destino no existe.");
                return;
            }

            String resultado = cuentaOrigen.transferirDinero(cuentaDestino, monto);
            lblMensaje.setText(resultado);

        } catch(NumberFormatException nfe){
            lblMensaje.setText("Monto inválido.");
        } catch(Exception ex){
            lblMensaje.setText("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
