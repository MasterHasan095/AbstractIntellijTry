package com.example.himtortons;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class individualElements {
    protected static List<Button> finalButtons = new ArrayList<>();
    //List of final buttons
    public static VBox menuListIndividual(Integer buttonIndex, File menuFile) throws IOException {

        VBox vbox = new VBox();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
        String line ;
        System.out.println("File reading succesfull");
        while((line = br.readLine()) != null){
            String[] eachLine = line.split(",");//Seperates each line cuz we need to get details for each item
            try{
                if(Integer.parseInt(eachLine[0]) == buttonIndex){//Only works for that major area
                    Button newButton = new Button();//Buttons to be added in our final buttons
                    GridPane tempGrid = new GridPane();
                    tempGrid.getStyleClass().add("grid_pane");
                    Label name = new Label(eachLine[1]);
                    name.getStyleClass().add("name");
                    Label price = new Label(eachLine[2]);
                    price.getStyleClass().add("price");
                    tempGrid.add(name,0,0);
                    tempGrid.add(price,1,0);
                    try{
                        Label calories = new Label(eachLine[3]);
                        calories.getStyleClass().add("calories");
                        tempGrid.add(calories,2,0);}
                    catch(Exception e){

                    }

                    newButton.setGraphic(tempGrid);
                    finalButtons.add(newButton);
                    vbox.getChildren().add(newButton);
                }

            }catch(Exception e){

            }

        }

        System.out.println("Method works");


        return vbox;
    }
}
