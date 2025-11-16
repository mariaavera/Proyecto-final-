module co.edu.uniquindio.poo.monederovirtual {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens co.edu.uniquindio.poo.monederovirtual.app to javafx.fxml;
    exports co.edu.uniquindio.poo.monederovirtual.app;

    opens model to javafx.fxml;
    exports model;
}