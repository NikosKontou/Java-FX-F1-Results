module com.example.cwrk_v2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens com.example.cwrk_v2 to javafx.fxml;
    exports com.example.cwrk_v2;
}