package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Cliente;
import model.Cuenta;
import model.MonederoVirtual;

public class MenuPrincipalController {

    private Cliente cliente;
    private Cuenta cuentaActiva;
    private MonederoVirtual monedero;

    @FXML private Button btnSalir;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuentaActiva = cliente.getCuentas().getFirst();
        }
    }

    @FXML
    void ConsultaSaldoaction(ActionEvent event) {
        cargarVentana("ConsultaSaldo.fxml",
                controller -> ((ConsultaSaldoController) controller).setCliente(cliente));
    }

    @FXML
    void DepositarDineroaction(ActionEvent event) {
        cargarVentana("depositarDinero.fxml",
                controller -> ((DepositarDineroController) controller).setCliente(cliente));
    }

    @FXML
    void RetirarDineroaction(ActionEvent event) {
        cargarVentana("retirarDinero.fxml",
                controller -> ((RetirarDineroController) controller).setCliente(cliente));
    }

    @FXML
    void TransferirDineroaction(ActionEvent event) {
        cargarVentana("transferencia.fxml",
                controller -> ((TransferenciaController) controller).inicializarConCliente(cliente));
    }

    @FXML
    void SistemaPuntosaction(ActionEvent event) {
        cargarVentana("SistemaPuntos.fxml",
                controller -> ((SistemaPuntosController) controller).setCliente(cliente));
    }

    @FXML
    void AnalizarGastosaction(ActionEvent event) {
        cargarVentana("AnalizarGastos.fxml",
                controller -> ((AnalizadorGastosController) controller).setCliente(cliente));
    }

    @FXML
    void ProgramarTransaccionaction(ActionEvent event) {
        cargarVentana("ProgramarTransaccion.fxml", controller -> {
            TransaccionProgramadaController c = (TransaccionProgramadaController) controller;
            c.inicializarConCliente(cliente);   // solo se envía el cliente
        });
    }

    @FXML
    void CrearCuentaaction(ActionEvent event) {
        cargarVentana("CrearCuenta.fxml",
                controller -> ((CrearCuentaController) controller).setCliente(cliente));
    }

    @FXML
    void ConsultaHistorialaction(ActionEvent event) {
        cargarVentana("ConsultarHistorial.fxml",
                controller -> ((ConsultarHistorialController) controller).setCliente(cliente));
    }


    @FXML
    void Saliraction(ActionEvent event) {
        ((Stage) btnSalir.getScene().getWindow()).close();
    }
    private void cargarVentana(String nombreFXML, ControladorAplicacion configuracion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/monederovirtual/" + nombreFXML));

            Parent root = loader.load();

            Object controller = loader.getController();
            configuracion.configurar(controller);

            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.err.println("ERROR cargando: " + nombreFXML);
            e.printStackTrace();
        }
    }

    private interface ControladorAplicacion {
        void configurar(Object controller);
    }
}
