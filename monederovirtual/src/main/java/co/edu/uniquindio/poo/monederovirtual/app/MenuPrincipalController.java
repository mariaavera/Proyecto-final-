package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Cliente;

import java.io.IOException;

public class MenuPrincipalController {
    private Cliente cliente;
    public void inicializarCliente(Cliente cliente){
        this.cliente = cliente;
    }

    @FXML
    private Button btnConsultarPuntos;

    @FXML
    private Button btnConsultarRango;

    @FXML
    private Button btnDepositarDinero;

    @FXML
    private Button btnRetirarDinero;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnTransferirDinero;

    @FXML
    void ConsultarPuntosaction(ActionEvent event) {
        System.out.println("Consultar puntos");
        // Aquí llamas la ventana de puntos
    }

    @FXML
    void ConsultarRangoaction(ActionEvent event) {
        System.out.println("Consultar rango");
    }

    @FXML
    void DepositarDineroaction(ActionEvent event) {
        try {
            abrirDepositar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RetirarDineroaction(ActionEvent event) {
        System.out.println("Retirar dinero");
    }

    @FXML
    void Saliraction(ActionEvent event) {
        System.out.println("Salir del programa");
        System.exit(0);
    }

    @FXML
    void TransferirDineroaction(ActionEvent event) {
        System.out.println("Transferir dinero");
    }
    @FXML
    public void abrirDepositar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("depositarDinero.fxml"));
        Parent root = loader.load();
        DepositarDineroController controller = loader.getController();
        controller.inicializarDatos(cliente.getCuenta());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

