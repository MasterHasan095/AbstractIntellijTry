package com.example.himtortons;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class POSMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane primaryPane = new AnchorPane();
        ScrollPane majorMenuPane = new ScrollPane();
        majorMenuPane.setStyle("-fx-background-color:aqua");
        StackPane minorMenuPane = new StackPane();
        minorMenuPane.setStyle("-fx-background-color:purple");
        ScrollPane orderPane = new ScrollPane();
        orderPane.setStyle("-fx-background-color:red");
        GridPane orderGrid = new GridPane();
        orderGrid.setStyle("-fx-background-color:green");
        primaryPane.getChildren().addAll(majorMenuPane,minorMenuPane,orderPane,orderGrid);
        AnchorPane.setTopAnchor(majorMenuPane, 0.0);    //
        AnchorPane.setLeftAnchor(majorMenuPane, 0.0);
        majorMenuPane.setPrefHeight(800);
        majorMenuPane.setPrefWidth(300);
        AnchorPane.setTopAnchor(orderPane,0.0);
        AnchorPane.setRightAnchor(orderPane,0.0);
        orderPane.setPrefHeight(400);
        orderPane.setPrefWidth(300);
        AnchorPane.setBottomAnchor(orderGrid,0.0);
        AnchorPane.setRightAnchor(orderGrid,0.0);
        orderGrid.setPrefHeight(400);
        orderGrid.setPrefWidth(300);
        AnchorPane.setTopAnchor(minorMenuPane,0.0);
        AnchorPane.setLeftAnchor(minorMenuPane,300.0);
        minorMenuPane.setPrefHeight(800);
        minorMenuPane.setPrefWidth(800);





        Scene scene = new Scene(primaryPane,1400,800);
        stage.setTitle("POS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
