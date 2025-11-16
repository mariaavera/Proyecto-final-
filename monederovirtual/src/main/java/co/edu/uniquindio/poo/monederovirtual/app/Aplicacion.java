package co.edu.uniquindio.poo.monederovirtual.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacion extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/co/edu/uniquindio/poo/monederovirtual/vistaPrincipal.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Monedero Virtual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

