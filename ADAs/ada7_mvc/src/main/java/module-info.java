module com.example.ada7_arq {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.ada7_arq.controller to javafx.fxml;

    exports com.example.ada7_arq.controller;
}
