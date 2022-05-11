module com.example.notakto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.notakto to javafx.fxml;
    exports com.example.notakto;
}