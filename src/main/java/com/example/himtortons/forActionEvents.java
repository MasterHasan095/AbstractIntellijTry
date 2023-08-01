package com.example.himtortons;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
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
        POSMain.minorMenuPane.getChildren().clear();
        Integer buttonIndex = buttonCreation.retrieveButtonIndex(button.getText());
        try {
            List<Object> tempList = POSMain.retrieveRestartType(new File("menuHimTortons.txt"),buttonIndex);
            POSMain.check = "null";
            if (!(tempList.size() == 2)) {

            }else{

                if (tempList.get(0).equals(1)){

                    POSMain.Varieties.setContent(minorPaneButton.returnRadioBoxSet(tempList));
                    POSMain.minorMenuPane.getChildren().add(POSMain.Varieties);
                    POSMain.radioButtons.get(0).setSelected(true);
                    POSMain.check = "radio";

                }else if (tempList.get(0).equals(2)){

                    POSMain.Varieties.setContent(minorPaneButton.returnComboBoxSet(tempList,buttonIndex));
                    POSMain.minorMenuPane.getChildren().add(POSMain.Varieties);
                    POSMain.check = "combo";
                }
            }
            System.out.println("Latest CheckPoint");
            POSMain.menuListIndividual.setContent(individualElements.menuListIndividual(buttonIndex,
                    new File("menuHimTortons.txt")));
            POSMain.minorMenuPane.getChildren().add(POSMain.menuListIndividual);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
