package com.example.himtortons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSMain extends Application {
    private List<Button> menuButtons = new ArrayList<>();
    private List<Pane> menuPanes = new ArrayList<>();
    private List<RadioButton> radioButtons = new ArrayList<>();
    private ArrayList listOf1s = new ArrayList<>();
    Map<String, Integer> dictionary = new HashMap<>();

    HBox sizes = new HBox();
    ScrollPane hBoxScrollPane = new ScrollPane(sizes);

    private boolean sizesAdded = false;


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
        VBox minorMenuPane = new VBox();
        hBoxScrollPane.getStyleClass().add("scroll-pane");
        sizes.setSpacing(10);
        sizes.setPadding(new Insets(0,0,0,10));
        sizes.setAlignment(Pos.CENTER);
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
                FlowPane menuPane = new FlowPane();
                dictionary.put(values[i],i+1);
                button.getStyleClass().add("button-style"); // Apply the CSS class
                menuButtons.add(button); // Add the button to the list
                menuPanes.add(menuPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        majorMenuVBox.getChildren().addAll(menuButtons);



        try (BufferedReader br = new BufferedReader(new FileReader(menuFile))) {
            String line = "";
            while (line!=null){

                String[] splitLine = line.split(",");
                List<Object> values = new ArrayList<>();
                for(String splitValue : splitLine){
                    try {
                        Integer intValue = Integer.parseInt(splitValue);
                        values.add(intValue);
                    }catch (NumberFormatException e){
                        values.add(splitValue);
                    }
                }
                if (values.get(0).equals("restart")) {
                    System.out.println(values.get(2));
                    if (values.get(2).equals(1)) {
                        listOf1s.add(values.get(1));
                        if (!sizesAdded) {
                            String[] sizeOptions = ((String) values.get(3)).split(":");
                            ToggleGroup sizesRadio = new ToggleGroup();
                            for (String option : sizeOptions) {
                                RadioButton radioButton = new RadioButton();
                                radioButton.setText(option);
                                radioButton.setToggleGroup(sizesRadio);
                                radioButton.getStyleClass().add("radio-button");
                                sizes.getChildren().add(radioButton);
                            }
                            sizesAdded = true;
                        }
                    }
                } line = br.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Button button : menuButtons) {
            button.setOnAction(e->{
                minorMenuPane.getChildren().clear();
                System.out.println(dictionary.get(button.getText()));
                if(listOf1s.contains(dictionary.get(button.getText()))){
                    System.out.println("working");
                    minorMenuPane.getChildren().add(hBoxScrollPane);
                }

            });
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
