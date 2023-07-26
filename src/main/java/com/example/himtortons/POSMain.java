package com.example.himtortons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    VBox minorMenuPane = new VBox();
    ScrollPane orderPane = new ScrollPane();
    GridPane orderGrid = new GridPane();


//Ye dekhte hai kya hai
    protected static List<Button> menuButtons = new ArrayList<>();
    private List<Pane> menuPanes = new ArrayList<>();
    private List<RadioButton> radioButtons = new ArrayList<>();
    private ArrayList listOf1s = new ArrayList<>();
    private ArrayList listOf2s = new ArrayList<>();
    static Map<String, Integer> dictionary = new HashMap<>();

    ScrollPane Varieties = new ScrollPane();
    static HBox varieties = new HBox();
    ScrollPane hBoxScrollPane = new ScrollPane(varieties);

    private boolean sizesAdded = false;
    private boolean varietiesAdded = false;


    @Override
    public void start(Stage stage) throws Exception {
        //Calling required method
        locationSet(); //Sets the location of all the Elements

        //Left Pane Button Creation
        majorMenuPane.setContent(buttonCreation.createFirstSetOfButtons(new File("menuHimTortons.txt")));
        for (Button button : menuButtons) {
            button.setOnAction(e->{
                minorMenuPane.getChildren().clear();
                Integer buttonIndex = buttonCreation.retrieveButtonIndex(button.getText());
                try {
                    List<Object> tempList = retrieveRestartType(new File("menuHimTortons.txt"),buttonIndex);
                    System.out.println("This is templist : " + tempList);
                    if (!(tempList.size() == 2)) {
                        System.out.println("for no options");
                    }else{
                        System.out.println("has sub options");
                        if (tempList.get(0).equals(1)){
                            System.out.println("Radio Boxes");
                            Varieties.setContent(minorPaneButton.returnRadioBoxSet(tempList));
                            minorMenuPane.getChildren().add(Varieties);
                        }else if (tempList.get(0).equals(2)){
                            System.out.println("Combo Boxes");
                            Varieties.setContent(minorPaneButton.returnComboBoxSet(tempList,buttonIndex));
                            minorMenuPane.getChildren().add(Varieties);
                        }
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //createUpperMinor();
            });
        }

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

        minorMenuPane.setStyle("-fx-background-color:purple");
        orderPane.setStyle("-fx-background-color:red");
        orderGrid.setStyle("-fx-background-color:green");
    }

    public void createUpperMinor(Integer buttonIndex){
        System.out.println("Method call working");
        System.out.println(buttonIndex);
    }
    public List<Object> retrieveRestartType(File menuFile, Integer buttonIndex) throws IOException {
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

    public void method(){
        File menuFile = new File("menuHimTortons.txt");
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
                                varieties.getChildren().add(radioButton);
                            }
                            sizesAdded = true;
                        }
                    }else if (values.get(2).equals(2)){

                        listOf2s.add(values.get(1));
                        if (!varietiesAdded) {
                            String[] varietyMajorOptions = ((String) values.get(3)).split(":");
                            ToggleGroup sizesRadio = new ToggleGroup();
                            for (String option : varietyMajorOptions) {
                                RadioButton radioButton = new RadioButton();
                                radioButton.setText(option);
                                radioButton.setToggleGroup(sizesRadio);
                                radioButton.getStyleClass().add("radio-button");
                                varieties.getChildren().add(radioButton);
                            }
                            varietiesAdded = true;
                        }
                    }
                } line = br.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

//Button action event set


