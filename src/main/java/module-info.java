module com.example.objectorientation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.objectorientation to javafx.fxml;
    exports com.example.objectorientation;
}