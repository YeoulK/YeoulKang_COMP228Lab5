module com.example.yeoulk_comm228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;



    opens com.example.yeoulk_comm228lab5 to javafx.fxml;
    exports com.example.yeoulk_comm228lab5;
}