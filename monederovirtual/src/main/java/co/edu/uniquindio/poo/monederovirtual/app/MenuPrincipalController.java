package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private Button btnConsultaHistorial;

    @FXML
    private Button btnConsultaSaldo;

    @FXML
    private Button btnDepositarDinero;

    @FXML
    private Button btnRetirarDinero;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSistemaPuntos;

    @FXML
    private Button btnTransferirDinero;

    @FXML
    void ConsultaHistorialAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultaHistorial.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnConsultaHistorial.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ConsultaSaldoAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultaSaldo.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnConsultaSaldo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DepositarDineroAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositarDinero.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnDepositarDinero.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RetirarDineroAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RetirarDinero.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnRetirarDinero.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SalirAction(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SistemaPuntosAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SistemaPuntos.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnSistemaPuntos.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void TransferirDineroAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransferirDinero.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnTransferirDinero.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
