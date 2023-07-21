package com.example.himtortons;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POSMain extends Application {
    private List<Button> menuButtons = new ArrayList<>();


    @Override
    public void start(Stage stage) throws Exception {


        //Setting All Panes

        AnchorPane primaryPane = new AnchorPane();
        primaryPane.getStylesheets().add(getClass().getResource("/com/example/himtortons/styles.css").toExternalForm());
        ScrollPane majorMenuPane = new ScrollPane();
        VBox majorMenuVBox = new VBox();
        majorMenuVBox.setSpacing(10);
        majorMenuVBox.setPadding(new Insets(0,0,0,25));
        majorMenuVBox.setAlignment(Pos.CENTER);
        majorMenuPane.setContent(majorMenuVBox);
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

        //All initial Panes Set

        //File Creation
        File menuFile = new File("menuHimTortons.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(menuFile))) {
            String line = br.readLine();
            String[] values = line.split(",");
            System.out.println(line);
            System.out.println(values.length);
            for (int i = 0; i < values.length; i++) {
                Button button = new Button(values[i]);
                button.getStyleClass().add("button-style"); // Apply the CSS class
                menuButtons.add(button); // Add the button to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        majorMenuVBox.getChildren().addAll(menuButtons);
        for (Button button : menuButtons) {
            button.setOnAction(e->{
                System.out.println("Yes");
            })   ;

        }





        Scene scene = new Scene(primaryPane,1400,800);
        stage.setTitle("POS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
