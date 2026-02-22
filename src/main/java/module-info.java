module com.example.taller {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.taller to javafx.fxml;
    exports com.example.taller;
    exports com.example.taller.Controllers;
    opens com.example.taller.Controllers to javafx.fxml;
}