module com.example.objectorientation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.objectorientation to javafx.fxml;
    exports com.example.objectorientation;
}