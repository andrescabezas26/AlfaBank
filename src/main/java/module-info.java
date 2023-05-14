module com.example.financialmovementmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.financialmovementmanager to javafx.fxml;
    exports com.example.financialmovementmanager;

    opens com.example.financialmovementmanager.controller to javafx.fxml;
    exports com.example.financialmovementmanager.controller;

    opens com.example.financialmovementmanager.model to javafx.fxml;
    exports com.example.financialmovementmanager.model;
}