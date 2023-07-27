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

public class minorPaneButton extends POSMain{
    protected static HashMap<String, String> hashMap = new HashMap();
    public static HBox returnRadioBoxSet(List<Object> values){
        varieties.getChildren().clear();
        POSMain.radioButtons.clear();
        String[] sizeOptions = ((String) values.get(1)).split(":");
        ToggleGroup sizesRadio = new ToggleGroup();
        for (String option : sizeOptions) {
            RadioButton radioButton = new RadioButton();
            radioButton.setText(option);
            radioButton.setToggleGroup(sizesRadio);
            radioButton.getStyleClass().add("radio-button");
            POSMain.radioButtons.add(radioButton);
            varieties.getChildren().add(radioButton);
        }

        return varieties;
    }

    public static HBox returnComboBoxSet(List<Object> values,Integer buttonIndex) throws IOException {

        varieties.getChildren().clear();

        String[] Options = ((String) values.get(1)).split(":");
        for (String option : Options) {
            Label label = new Label();
            label.getStyleClass().add("radio-button");
            VBox tempVBox = new VBox();
            ComboBox combobox = comboBoxCreation(buttonIndex,option);
            combobox.getStyleClass().add("radio-button");
            label.setGraphic(combobox);
            tempVBox.getChildren().addAll(label);
            varieties.getChildren().add(tempVBox);

        }

        return varieties;
    }

    public static ComboBox<String> comboBoxCreation(Integer buttonIndex,String optionName) throws IOException {
        ComboBox<String> comboBox1 = new ComboBox<>();
        HashMap<String,String> listOfComboBoxValues = retrieveComboBoxDropDown(new File("menuHimTortons.txt"),
                buttonIndex);

        String[] finalComboBoxList = listOfComboBoxValues.get(optionName).split(":");

        for(String value : finalComboBoxList){
            comboBox1.getItems().add(value);
            comboBox1.setValue(optionName);
        }

        return comboBox1;
    }

    public static HashMap retrieveComboBoxDropDown(File menuFile, Integer buttonIndex) throws IOException {

        List<String> comboBoxValues = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(menuFile));
        String line;
        while ((line = br.readLine()) != null) {
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
                    if (!values.get(2).equals(0)) {
                        String comboStuff = (String) values.get(3);
                        String[] postSplit = comboStuff.split(":");
                        String lineNew = br.readLine();
                        String[] minorMinorSplit = lineNew.split(",");
                        for(int i = 0;i<postSplit.length;i++){
                            hashMap.put(postSplit[i],minorMinorSplit[i]);
                        }
                    }
                }
            }
        }

        return hashMap;
    }
}

