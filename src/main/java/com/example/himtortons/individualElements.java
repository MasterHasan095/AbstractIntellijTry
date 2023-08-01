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
    public static VBox menuListIndividual(Integer buttonIndex, File menuFile) throws IOException {
        VBox vbox = new VBox();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
        String line ;
        System.out.println("File reading succesfull");
        while((line = br.readLine()) != null){
            String[] Systumm = line.split(",");
            try{
                if(Integer.parseInt(Systumm[0]) == buttonIndex){
                    Button newButton = new Button();
                    GridPane tempGrid = new GridPane();
                    Label name = new Label(Systumm[1]);
                    Label price = new Label(Systumm[2]);
                    try{
                        Label calories = new Label(Systumm[3]);
                        tempGrid.add(calories,2,0);}
                    catch(Exception e){

                    }
                    tempGrid.add(name,0,0);
                    tempGrid.add(price,1,0);
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
