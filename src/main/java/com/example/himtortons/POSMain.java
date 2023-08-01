package com.example.himtortons; //PACKAGE


//IMPORTING STUFF
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSMain extends Application {


    //Creating the initial Pane
    AnchorPane primaryPane = new AnchorPane(); //Basically the whole scene

    //4 major Panes
    ScrollPane majorMenuPane = new ScrollPane();
    static VBox minorMenuPane = new VBox();
    static ScrollPane orderPane = new ScrollPane();
    static VBox orderGridArea = new VBox();
    static GridPane orderGrid = new GridPane();
    //4 Major Panes ends


    //List of buttons to create onClick
    protected static List<Button> menuButtons = new ArrayList<>();
    protected static List<Button> removeFromCartButtons = new ArrayList<>();

    //List of buttons to create onClick ends

    //Top area of middle pane
    protected static List<RadioButton> radioButtons = new ArrayList<>();
    protected static List<ComboBox> comboBoxes = new ArrayList<>();
    protected static String check = null; // Used to check radio box or combobox

    //Top area of middle pane ends

    //Dictionary is a hashmap, but named dictionary because our first language was python
    static Map<String, Integer> dictionary = new HashMap<>();
    static ScrollPane menuListIndividual = new ScrollPane();
    static ScrollPane Varieties = new ScrollPane();
    static HBox varieties = new HBox();
    ScrollPane hBoxScrollPane = new ScrollPane(varieties);



    @Override
    public void start(Stage stage) throws Exception {
        //Calling required method
        locationSet(); //Sets the location of all the Elements

        //Left Pane Button Creation
        majorMenuPane.setContent(buttonCreation.createFirstSetOfButtons(new File("menuHimTortons.txt"))); //Setting
        // the content as a VBox which contains a list of all the majorMenuButtons.
        for (Button button : menuButtons) { //Iterates through each button in the menu button we created in the
            // button Creation class.
            button.setOnAction(e->{ // We create an on action event for all the major menu pane buttons.
                forActionEvents.onClickingMajorButtons(button);
                //createUpperMinor();
                for(Button innerButton:individualElements.finalButtons){
                    innerButton.setOnAction(Event->{
                        receiptGeneration.ButtonListGeneration(forActionEvents.forCartText(innerButton));
                        orderPane.setContent(receiptGeneration.receiptGenerated(removeFromCartButtons));
                        System.out.println("Checkpoint Initial \n" + removeFromCartButtons);
                    });

                }
            });
        }


        for (Button button : removeFromCartButtons){
            System.out.println("Checkpoint 1");
            button.setOnAction(e->{
                System.out.println("Checkpoint 2");
                removeFromCartButtons.remove(button);
                System.out.println(removeFromCartButtons);
                orderPane.setContent(receiptGeneration.receiptGenerated(removeFromCartButtons));

            });
        }
        orderPane.setContent(receiptGeneration.receiptGenerated(removeFromCartButtons));


        hBoxScrollPane.getStyleClass().add("scroll-pane");

        varieties.setSpacing(10);
        varieties.setPadding(new Insets(0,0,0,10));
        varieties.setAlignment(Pos.CENTER);

        primaryPane.getChildren().addAll(majorMenuPane,minorMenuPane,orderPane,orderGridArea);

        Scene scene = new Scene(primaryPane,1400,800);
        String cssFile = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setTitle("POS");
        stage.setScene(scene);
        stage.show();
    }
    public void locationSet(){ //Sets all pane location
        AnchorPane.setTopAnchor(majorMenuPane, 0.0);
        AnchorPane.setLeftAnchor(majorMenuPane, 0.0);
        majorMenuPane.setPrefHeight(800);
        majorMenuPane.setPrefWidth(300);
        AnchorPane.setTopAnchor(minorMenuPane,0.0);
        AnchorPane.setLeftAnchor(minorMenuPane,300.0);
        minorMenuPane.setPrefHeight(800);
        minorMenuPane.setPrefWidth(800);
        AnchorPane.setTopAnchor(orderPane,0.0);
        AnchorPane.setRightAnchor(orderPane,0.0);
        orderPane.setPrefHeight(400);
        orderPane.setPrefWidth(300);
        AnchorPane.setBottomAnchor(orderGridArea,0.0);
        AnchorPane.setRightAnchor(orderGridArea,0.0);
        orderGridArea.setPrefHeight(400);
        orderGridArea.setPrefWidth(300);
        amountButtons.makeGrid();
        orderGridArea.getChildren().addAll(orderGrid,amountButtons.cashAndCard());


        orderGrid.setStyle("-fx-background-color:green");
    }






    public static void main(String[] args) {
        launch(args);
    }




}

//Button action event set