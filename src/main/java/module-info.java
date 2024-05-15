module org.intento2.example {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.intento2.example to javafx.fxml;
    exports org.intento2.example;
}