package com.example.himtortons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FlowPane primaryPane = new FlowPane();


        Scene scene = new Scene(primaryPane, getDeviceWidth(), getDeviceHeight());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    //Scene Size Setting
    private double getDeviceWidth() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    private double getDeviceHeight() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}