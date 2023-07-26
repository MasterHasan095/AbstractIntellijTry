package com.example.himtortons;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class minorPaneButton extends POSMain{
    public static HBox returnRadioBoxSet(List<Object> values){
        varieties.getChildren().clear();

        String[] sizeOptions = ((String) values.get(1)).split(":");
        ToggleGroup sizesRadio = new ToggleGroup();
        for (String option : sizeOptions) {
            RadioButton radioButton = new RadioButton();
            radioButton.setText(option);
            radioButton.setToggleGroup(sizesRadio);
            radioButton.getStyleClass().add("radio-button");
            varieties.getChildren().add(radioButton);
            System.out.println(option);
        }

        return varieties;
    }

    public static HBox returnComboBoxSet(List<Object> values,Integer buttonIndex) throws IOException {

        varieties.getChildren().clear();

        String[] Options = ((String) values.get(1)).split(":");
        for (String option : Options) {
            Label label = new Label();
            label.setText(option);
            label.getStyleClass().add("combo-button");
            VBox tempVBox = new VBox();
            ComboBox combobox = comboBoxCreation(buttonIndex);
            tempVBox.getChildren().addAll(label,combobox);
            varieties.getChildren().add(tempVBox);
            System.out.println("Working" + option);
        }

        return varieties;
    }

    public static ComboBox<String> comboBoxCreation(Integer buttonIndex) throws IOException {
        ComboBox<String> comboBox1 = new ComboBox<>();
        List<String> listOfComboBoxValues = retrieveComboBoxDropDown(new File("menuHimTortons.txt"),buttonIndex);

        for(String value : listOfComboBoxValues){
            comboBox1.getItems().add(value);
        }

        return comboBox1;
    }

    public static List<String> retrieveComboBoxDropDown(File menuFile, Integer buttonIndex) throws IOException {
        System.out.println("retrieveComboBoxDropDown");
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
                        for (String majorType : postSplit) {
                            line = br.readLine();
                            String[] anotherSplit = line.split(",");
                            //comboBoxValues.add(majorType);
                            for (String minorType: anotherSplit){
                                comboBoxValues.add(minorType);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Drop down data ends here");
        return comboBoxValues;
    }
}

