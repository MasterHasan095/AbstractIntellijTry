package com.example.himtortons;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class minorPaneButton {
    protected static HashMap<String, String> hashMap = new HashMap();
    public static HBox returnRadioBoxSet(List<Object> values){ // HBox is returned and set as the content for// scrollpane Varieties
        POSMain.varieties.getChildren().clear();//MAkes sure the HBox is empty
        POSMain.radioButtons.clear();//Makes sure list of radio buttons is empty
        String[] subOptions = ((String) values.get(1)).split(":"); //1st Index of values contains the sub options
        ToggleGroup sizesRadio = new ToggleGroup();// Toggle Group wich contains the radio Buttons
        for (String option : subOptions) {// subOptiosn contains the subOptions after splitting by a :
            RadioButton radioButton = new RadioButton();
            radioButton.setText(option);
            radioButton.setToggleGroup(sizesRadio);
            radioButton.getStyleClass().add("radio-button");//Adds class to the radio Button
            POSMain.radioButtons.add(radioButton);//Adds to the list of radioButtons
            POSMain.varieties.getChildren().add(radioButton);//Add the radioButtons list a HBox
        }

        return POSMain.varieties;//HBox is returned which is set as the content for scrollpane
    }

    public static HBox returnComboBoxSet(List<Object> values,Integer buttonIndex) throws IOException {

        POSMain.varieties.getChildren().clear();//Makes sure the HBox is cleared

        String[] Options = ((String) values.get(1)).split(":");//Splits the values at first index to retrieve suboptions
        for (String option : Options) {//iterates for each options
            Label label = new Label();
            label.getStyleClass().add("minor-label");//Adds design
            VBox tempVBox = new VBox();//WIll contain all the suboptions
            ComboBox combobox = comboBoxCreation(buttonIndex,option);//Comboboxes are created here
            combobox.getStyleClass().add("combo-button");
            label.setGraphic(combobox);
            tempVBox.getChildren().addAll(label);
            POSMain.varieties.getChildren().add(tempVBox);//A VBox is passed to an HBox

        }

        return POSMain.varieties;
    }

    public static ComboBox<String> comboBoxCreation(Integer buttonIndex,String optionName) throws IOException {
        ComboBox<String> comboBox1 = new ComboBox<>();
        HashMap<String,String> listOfComboBoxValues = retrieveComboBoxDropDown(new File("menuHimTortons.txt"),
                buttonIndex);

        String[] finalComboBoxList = listOfComboBoxValues.get(optionName).split(":");//Differentiates the suboption
        // list by : which results in the sub options of the suboptions, which will go in the drop down.

        for(String value : finalComboBoxList){
            comboBox1.getItems().add(value);

        }
        comboBox1.setValue(optionName);
        POSMain.comboBoxes.add(comboBox1);
        return comboBox1;
    }

    public static HashMap retrieveComboBoxDropDown(File menuFile, Integer buttonIndex) throws IOException {

        List<String> comboBoxValues = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
        String line;
        while ((line = br.readLine()) != null) {
            /*
            * Splits the line, and keeps strings as strings and integers as integers
            *
            * */
            String[] splitLine = line.split(",");
            List<Object> values = new ArrayList<>();
            for (String splitValue : splitLine) {
                try {
                    Integer intValue = Integer.parseInt(splitValue);
                    values.add(intValue);
                } catch (NumberFormatException e) {
                    values.add(splitValue);
                }
            }

            if (values.get(0).equals("restart")) {
                if (values.get(1).equals(buttonIndex)) {
                    if (!values.get(2).equals(0)) {//Whenever it is not 0, i.e radio or combo
                        String comboStuff = (String) values.get(3);
                        String[] postSplit = comboStuff.split(":");//Sub options
                        String lineNew = br.readLine();//Next line which contains sub of suboptions
                        System.out.println(lineNew);
                        String[] minorMinorSplit = lineNew.split(",");//seperates the line and keeps e=sub option for
                        System.out.println(minorMinorSplit);
                        // each sub option different
                        for(int i = 0;i<postSplit.length;i++){
                            System.out.println("This is postsplit" + postSplit[i]);
                            hashMap.put(postSplit[i],minorMinorSplit[i]);//Adds to a hashmap, the sub-option and the
                            // list of sub options of sub options.
                        }
                    }
                }
            }
        }

        return hashMap;
    }
}

