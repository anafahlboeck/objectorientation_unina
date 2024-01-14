module com.example.objectorientation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.objectorientation to javafx.fxml;
    exports com.example.objectorientation;
    exports com.example.objectorientation.model;
    opens com.example.objectorientation.model to javafx.fxml;
    exports com.example.objectorientation.service;
    opens com.example.objectorientation.service to javafx.fxml;
    exports com.example.objectorientation.controller;
    opens com.example.objectorientation.controller to javafx.fxml;
}