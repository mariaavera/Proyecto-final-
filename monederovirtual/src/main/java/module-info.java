module co.edu.uniquindio.poo.monederovirtual {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens co.edu.uniquindio.poo.monederovirtual to javafx.fxml;
    exports co.edu.uniquindio.poo.monederovirtual;
}