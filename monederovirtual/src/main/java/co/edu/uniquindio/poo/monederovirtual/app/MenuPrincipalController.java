package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AnalizadorGastos;
import model.Cliente;
import model.Cuenta;
import model.MonederoVirtual;

import java.io.IOException;

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
    @FXML
    private void abrirAnalizador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnalizadorGastos.fxml"));
            Scene scene = new Scene(loader.load());
            AnalizadorGastosController controller = loader.getController();

            AnalizadorGastos analizador = new AnalizadorGastos(miCuenta);
            controller.setAnalizadorGastos(analizador);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Analizador de Gastos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void abrirTransaccionesProgramadas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transaccionesProgramadas.fxml"));
            Parent root = loader.load();
            TransaccionProgramadaController controller = loader.getController();
            controller.inicializarDatos(monedero, cliente);
            Stage stage = new Stage();
            stage.setTitle("Programar Transacción");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
