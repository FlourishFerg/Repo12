module com.example.repo12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.repo12 to javafx.fxml;
    exports com.example.repo12;
}