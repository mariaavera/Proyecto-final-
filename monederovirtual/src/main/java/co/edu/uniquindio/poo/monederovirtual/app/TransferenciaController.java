package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BaseInformaciónCliente;
import model.Cliente;
import model.Cuenta;

import javafx.scene.control.Label;

public class TransferenciaController {

    @FXML private ComboBox<String> txtCuentaOrigen;
    @FXML private TextField txtValorATransferir;
    @FXML private TextField txtCuentaDestinatario;
    @FXML private Label lblMensaje;

    private Cuenta cuentaOrigen;

    public void inicializarConCliente(Cliente cliente){
        txtCuentaOrigen.getItems().clear();
        for(Cuenta c : cliente.getCuentas()){
            txtCuentaOrigen.getItems().add(c.getId());
        }
        if(!txtCuentaOrigen.getItems().isEmpty()){
            txtCuentaOrigen.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void TransferirDineroaction(){
        try{

            String numeroOrigen = txtCuentaOrigen.getValue();
            if(numeroOrigen == null){
                lblMensaje.setText("Seleccione la cuenta origen.");
                return;
            }

            cuentaOrigen = BaseInformaciónCliente.buscarCuentaPorNumero(numeroOrigen);

            double monto = Double.parseDouble(txtValorATransferir.getText().trim().replace(",", "."));
            String numeroDestino = txtCuentaDestinatario.getText().trim();

            Cuenta cuentaDestino = BaseInformaciónCliente.buscarCuentaPorNumero(numeroDestino);
            if(cuentaDestino == null){
                lblMensaje.setText("La cuenta destino no existe.");
                return;
            }
            String resultado = cuentaOrigen.transferirDinero(cuentaDestino, monto);
            lblMensaje.setText(resultado);

            if(resultado.toLowerCase().contains("éxito") || resultado.toLowerCase().contains("exitosa")){

                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> Volveraction());
                pause.play();
            }

        } catch(NumberFormatException nfe){
            lblMensaje.setText("Monto inválido.");
        } catch(Exception ex){
            lblMensaje.setText("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    @FXML
    private void Volveraction() {
        Stage stage = (Stage) txtCuentaOrigen.getScene().getWindow();
        stage.close();
    }
}
