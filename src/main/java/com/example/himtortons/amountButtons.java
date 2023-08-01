package com.example.himtortons;

import javafx.scene.layout.GridPane;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class amountButtons {

    static Button button1 = new Button("1");

    static Button button2 = new Button("2");
    static Button button3 = new Button("3");
    static Button button4 = new Button("4");
    static Button button5 = new Button("5");
    static Button button6 = new Button("6");
    static Button button7 = new Button("7");
    static Button button8 = new Button("8");
    static Button button9 = new Button("9");
    public static GridPane makeGrid(){
        POSMain.orderGrid.add(button1,0,0);
        button1.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button2,1,0);
        button2.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button3,2,0);
        button3.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button4,0,1);
        button4.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button5,1,1);
        button5.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button6,2,1);
        button6.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button7,0,2);
        button7.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button8,1,2);
        button8.getStyleClass().add("grid-button");
        POSMain.orderGrid.add(button9,2,2);
        button9.getStyleClass().add("grid-button");
        return POSMain.orderGrid;
    }


    static Button cash = new Button("CASH");
    static Button card = new Button("CARD");

    public static HBox cashAndCard(){
        HBox newHbox = new HBox();
        card.getStyleClass().add("lower-buttons");
        cash.getStyleClass().add("lower-buttons");
        newHbox.getChildren().addAll(cash,card);
        return newHbox;
    }



}
