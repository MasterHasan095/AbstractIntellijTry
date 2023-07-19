module com.example.himtortons {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.himtortons to javafx.fxml;
    exports com.example.himtortons;
}