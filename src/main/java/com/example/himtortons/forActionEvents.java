package com.example.himtortons;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class forActionEvents {
    public static String forCartText(Button innerButton){
        GridPane tempGridPane = (GridPane) innerButton.getGraphic();
        Label name = (Label) tempGridPane.getChildren().get(0);
        Label price = (Label) tempGridPane.getChildren().get(1);
        Label calories = (Label) tempGridPane.getChildren().get(2);
        String variety = null;
        String receiptLine = null;
        switch (POSMain.check){
            case "radio":
                for(RadioButton option : POSMain.radioButtons){
                    if(option.isSelected()){
                        variety = option.getText();
                    }
                }
                receiptLine = variety + " "+ name.getText() + " : " + price.getText();

                break;
            case "combo":
                System.out.println("We solve combo here");
                String miniVariety = "\n";
                for(ComboBox option : POSMain.comboBoxes){
                    miniVariety += option.getSelectionModel().getSelectedItem() + " ";
                }
                receiptLine = name.getText() + " : " + price.getText() + miniVariety;
                break;
            default:
                receiptLine = name.getText() + " : " + price.getText();
        }
        return receiptLine;
    }

    public static void onClickingMajorButtons(Button button){
        POSMain.minorMenuPane.getChildren().clear();//MAkes sure that the top area is clear so that we dont keep
        // stacking panes on top of each other.
        Integer buttonIndex = buttonCreation.retrieveButtonIndex(button.getText()); // Retrieves the index of the
        // button so that we can iterate further.
        try {
            List<Object> tempList = retrieveRestartType(new File("menuHimTortons.txt"),buttonIndex); // To check for
            // radiobox, combobox, or none
            POSMain.check = "null"; //check field is used further to see if it is radio or combobbox
            if ((tempList.size() == 2)) {//if length is 2, it is quite obvious that it has sub options.
                if (tempList.get(0).equals(1)){ // For Radio
                    POSMain.Varieties.setContent(minorPaneButton.returnRadioBoxSet(tempList));//Scrollpane contains
                    // HBox now
                    POSMain.minorMenuPane.getChildren().add(POSMain.Varieties); //minorMenuPane is a VBox
                    POSMain.radioButtons.get(0).setSelected(true);//Sets the first as selected
                    POSMain.check = "radio";//Keeps the check as radio which is used further
                }else if (tempList.get(0).equals(2)){ // For ComboBox
                    POSMain.Varieties.setContent(minorPaneButton.returnComboBoxSet(tempList,buttonIndex));
                    POSMain.minorMenuPane.getChildren().add(POSMain.Varieties);
                    POSMain.check = "combo";
                }
            }

            POSMain.menuListIndividual.setContent(individualElements.menuListIndividual(buttonIndex,
                    new File("menuHimTortons.txt")));
            POSMain.minorMenuPane.getChildren().add(POSMain.menuListIndividual);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Object> retrieveRestartType(File menuFile, Integer buttonIndex) throws IOException { // To// check the type after restart is encountered.
        List<Object> restartList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
        String line = "";
        while (line!=null){//Stop traversing the code when menu ends
            String[] splitLine = line.split(",");//It is a CSV format text file
            List<Object> values = new ArrayList<>();
            for(String splitValue : splitLine){//Accessing all the elements of a list generated from a line in a csv
                // file
                try {
                    Integer intValue = Integer.parseInt(splitValue); // If it is an integer
                    values.add(intValue);
                }catch (NumberFormatException e){
                    values.add(splitValue); // If it is not an integer
                }
            }
            if(values.get(0).equals("restart")){//For every line which has the first index as equal
                if (values.get(1).equals(buttonIndex)){//So that it only runs for that specific button, buttonIndex
                    // was retrieved for this specific part of code
                    restartList.add((Integer) values.get(2));//Adds the third index to the restart list, i.e radio or
                    // combo box
                    if (!values.get(2).equals(0)){
                        restartList.add(values.get(3));//For radio or combo boxes we also have sub options, which is
                        // why we return them too
                    }
                }
            }
            line = br.readLine();//Reads the next line
        }return restartList;// Returns the list with sub-options, if applicable
    }
}
