package com.example.himtortons;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class POSMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Button button = new Button("Nice");
        button.setOnAction(actionEvent -> {
            paymentMethod paymentMethod = new paymentMethod("CASH",50,25);
        });
        pane.getChildren().add(button);
        Scene scene = new Scene(pane,500,500);
        stage.setTitle("POS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
