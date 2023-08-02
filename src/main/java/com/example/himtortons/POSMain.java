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
    static VBox toprightOrderPane = new VBox();

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

    //Total cost variable
    protected static double instanceAmount = 0;
    protected static double discountAmount = 0;
    static Label amountLabel = new Label();
    public static String filename = "northernSmokes.txt";

    //Hashmap which stores name and price

    protected static HashMap<String, Double> nameAndPrice = new HashMap<>();



    @Override
    public void start(Stage stage) throws Exception {

        //Calling required method
        locationSet(); //Sets the location of all the Elements

        //Left Pane Button Creation
        majorMenuPane.setContent(buttonCreation.createFirstSetOfButtons(new File(filename))); //Setting
        // the content as a VBox which contains a list of all the majorMenuButtons.
        for (Button button : menuButtons) { //Iterates through each button in the menu button we created in the
            // button Creation class.
            button.setOnAction(e->{ // We create an on action event for all the major menu pane buttons.
                forActionEvents.onClickingMajorButtons(button);
                //createUpperMinor();
                for(Button innerButton:individualElements.finalButtons){
                    innerButton.setOnAction(Event->{
                        System.out.println("We are at this button");
                        GridPane tempGrid = (GridPane) innerButton.getGraphic();
                        Label lbl = (Label) tempGrid.getChildren().get(1);
                        instanceAmount += Double.parseDouble(lbl.getText());
                        System.out.println(instanceAmount);
                        amountLabel.setText("Total : " + instanceAmount + "    Discount Amount : " + discountAmount);
                        nameAndPrice.put(forActionEvents.forCartText(innerButton),Double.parseDouble(lbl.getText()));
                        receiptGeneration.ButtonListGeneration(forActionEvents.forCartText(innerButton));
                        orderPane.setContent(receiptGeneration.receiptGenerated(removeFromCartButtons));
                    });

                }
            });
        }



        orderPane.setContent(receiptGeneration.receiptGenerated(removeFromCartButtons));


        hBoxScrollPane.getStyleClass().add("scroll-pane");

        varieties.setSpacing(10);
        varieties.setPadding(new Insets(0,0,0,10));
        varieties.setAlignment(Pos.CENTER);

        primaryPane.getChildren().addAll(majorMenuPane,minorMenuPane,toprightOrderPane,orderGridArea);

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
        AnchorPane.setTopAnchor(toprightOrderPane,0.0);
        AnchorPane.setRightAnchor(toprightOrderPane,0.0);
        toprightOrderPane.setPrefHeight(400);
        toprightOrderPane.setPrefWidth(300);
        orderPane.setPrefHeight(350);
        amountLabel.setPrefHeight(50);
        amountLabel.setPrefWidth(300);
        amountLabel.setText("Total : " + instanceAmount);
        toprightOrderPane.getChildren().addAll(orderPane,amountLabel);
        AnchorPane.setBottomAnchor(orderGridArea,0.0);
        AnchorPane.setRightAnchor(orderGridArea,0.0);
        orderGridArea.setPrefHeight(400);
        orderGridArea.setPrefWidth(300);
        amountButtons.makeGrid();
        orderGridArea.getChildren().addAll(orderGrid,amountButtons.discountSet(),amountButtons.cashAndCard());
        Varieties.setPrefHeight(100);
        varieties.setPrefHeight(100);


        orderGrid.setStyle("-fx-background-color:green");
    }






    public static void main(String[] args) {
        launch(args);
    }




}

//Button action event set