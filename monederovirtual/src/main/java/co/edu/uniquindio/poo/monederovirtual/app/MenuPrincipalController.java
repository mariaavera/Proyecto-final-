package co.edu.uniquindio.poo.monederovirtual.app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalController {
    private Cliente cliente;

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
    void ConsultaHistorialaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/ConsultarHistorial.fxml")
            );
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnConsultaHistorial.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Cliente clienteActual;

    public void setCliente(Cliente cliente) {
        this.clienteActual = cliente;
    }
    @FXML
    void ConsultaSaldoaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/ConsultarSaldo.fxml")
            );
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnConsultaSaldo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DepositarDineroaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/DepositarDinero.fxml")
            );
                    Parent root = loader.load();

                    DepositarDineroController controller = loader.getController();
                    controller.setCliente(clienteActual);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    @FXML
    void RetirarDineroaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/RetirarDinero.fxml")
            );
            Parent root = loader.load();
            RetirarDineroController controller = loader.getController();
            controller.setCliente(clienteActual);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Saliraction(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SistemaPuntosaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/SistemaPuntos.fxml")
            );
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnSistemaPuntos.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void TransferirDineroaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/Transferencia.fxml")
            );
            Parent root = loader.load();
            TransferenciaController controller = loader.getController();
            controller.inicializarConCliente(clienteActual);
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnTransferirDinero.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void AnalizadorGastosaction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/AnalizadorGasto.fxml")
            );
            Parent root = loader.load();
            AnalizadorGastosController controller = loader.getController();
            List<Cuenta> cuentasCliente = cliente.getCuentas();
            if (cuentasCliente == null) {
                cuentasCliente = new ArrayList<>();
            }
            AnalizadorGastos analizador = new AnalizadorGastos(cliente);
            controller.setAnalizadorGastos(analizador);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Analizador de Gastos");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ProgramarTransaccionaction() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/ProgramarTransaccion.fxml")
            );
            Parent root = loader.load();
            TransaccionProgramadaController controller = loader.getController();
            controller.inicializarDatos(cliente.getMonederoPrincipal(), cliente);
            Stage stage = new Stage();
            stage.setTitle("Programar Transacción");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarMenu() {
        System.out.println("Usuario: " + cliente.getNombre());
        System.out.println("Puntos: " + cliente.getPuntos());
}
}
