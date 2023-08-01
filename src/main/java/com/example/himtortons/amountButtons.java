package com.example.himtortons;

import javafx.scene.layout.GridPane;

import java.awt.*;
import javafx.scene.control.Button;

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
        POSMain.orderGrid.add(button2,1,0);
        POSMain.orderGrid.add(button3,2,0);
        POSMain.orderGrid.add(button4,0,1);
        POSMain.orderGrid.add(button5,1,1);
        POSMain.orderGrid.add(button6,2,1);
        POSMain.orderGrid.add(button7,0,2);
        POSMain.orderGrid.add(button8,1,2);
        POSMain.orderGrid.add(button9,2,2);


        return POSMain.orderGrid;
    }


    Button cash = new Button("CASH");
    Button card = new Button("CARD");



}
