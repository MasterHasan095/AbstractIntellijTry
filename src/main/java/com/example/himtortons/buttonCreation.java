package com.example.himtortons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class buttonCreation  extends POSMain{
    public static VBox createFirstSetOfButtons(File filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                Button button = new Button(values[i]);
                dictionary.put(values[i],i+1);
                button.getStyleClass().add("button-style"); // Apply the CSS class
                menuButtons.add(button); // Add the button to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox majorMenuVBox = new VBox();
        majorMenuVBox.setSpacing(10);
        majorMenuVBox.setPadding(new Insets(0,0,0,25));
        majorMenuVBox.getChildren().addAll(menuButtons);
        return majorMenuVBox;
    }
    public static Integer retrieveButtonIndex(String text){
        return dictionary.get(text);
    }
}
