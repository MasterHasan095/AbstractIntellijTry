package com.example.himtortons;

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
    AnchorPane primaryPane = new AnchorPane();

    //4 major Panes
    ScrollPane majorMenuPane = new ScrollPane();
    static VBox minorMenuPane = new VBox();
    static ScrollPane orderPane = new ScrollPane();
    static GridPane orderGrid = new GridPane();


    //Ye dekhte hai kya hai
    protected static List<Button> menuButtons = new ArrayList<>();
    protected static List<Button> removeFromCartButtons = new ArrayList<>();
    protected static String check = null;

    private List<Pane> menuPanes = new ArrayList<>();
    protected static List<RadioButton> radioButtons = new ArrayList<>();
    protected static List<ComboBox> comboBoxes = new ArrayList<>();
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
        majorMenuPane.setContent(buttonCreation.createFirstSetOfButtons(new File("menuHimTortons.txt")));
        for (Button button : menuButtons) {
            button.setOnAction(e->{
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

        primaryPane.getChildren().addAll(majorMenuPane,minorMenuPane,orderPane,orderGrid);

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
        AnchorPane.setBottomAnchor(orderGrid,0.0);
        AnchorPane.setRightAnchor(orderGrid,0.0);
        orderGrid.setPrefHeight(400);
        orderGrid.setPrefWidth(300);
        amountButtons.makeGrid();


        orderGrid.setStyle("-fx-background-color:green");
    }


    public static List<Object> retrieveRestartType(File menuFile, Integer buttonIndex) throws IOException {
        List<Object> restartIndexList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
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
            if(values.get(0).equals("restart")){
                if (values.get(1).equals(buttonIndex)){
                    restartIndexList.add((Integer) values.get(2));
                    if (!values.get(2).equals(0)){
                        restartIndexList.add(values.get(3));
                    }
                }
            }
            line = br.readLine();
        }return restartIndexList;
    }



    public static void main(String[] args) {
        launch(args);
    }




}

//Button action event set