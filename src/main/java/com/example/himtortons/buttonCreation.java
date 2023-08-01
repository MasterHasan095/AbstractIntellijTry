package com.example.himtortons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class buttonCreation{
    public static VBox createFirstSetOfButtons(File filename){ //Creates the left set of buttons
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); //Reads the first line
            String[] values = line.split(","); // Splits the first line on basis of commas which results in
            // retrieving only major menus as a list
            for (int i = 0; i < values.length; i++) {//Iterates through all hot drinks
                Button button = new Button(values[i]); //Creates new buttons for major menus
                POSMain.dictionary.put(values[i],i+1);//Adds to the dictionary taking the text as key and number as
                // value
                button.getStyleClass().add("button-style"); // Apply the CSS class
                POSMain.menuButtons.add(button); // Add the button to the list on which we can apply on action event
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox majorMenuVBox = new VBox(); // A vertical box which would contain a list of buttons, this is set as the
        // content of the scrollpane.
        majorMenuVBox.setSpacing(10);
        majorMenuVBox.setPadding(new Insets(0,0,0,25));
        majorMenuVBox.getChildren().addAll(POSMain.menuButtons);//Adds the list of buttons to the VBox
        return majorMenuVBox; //Returns the VBox which is set as the content of the scrollpane
    }
    public static Integer retrieveButtonIndex(String text){
        return POSMain.dictionary.get(text);
    }
}
