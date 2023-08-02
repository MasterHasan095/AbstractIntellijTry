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


    static Button discount5 = new Button("5%");
    static Button discount10 = new Button("10%");
    static Button discount20 = new Button("20%");
    static Button cash = new Button("CASH");
    static Button card = new Button("CARD");

    public static HBox discountSet(){
        discount5.getStyleClass().add("discount_button");
        discount10.getStyleClass().add("discount_button");
        discount20.getStyleClass().add("discount_button");
        discount5.setOnAction(e->{
            POSMain.discountAmount= POSMain.instanceAmount*0.05;
            POSMain.instanceAmount -= POSMain.discountAmount;
            if(POSMain.instanceAmount <=0){
                POSMain.instanceAmount = 0;
            }
            POSMain.amountLabel.setText("Total : " + POSMain.instanceAmount + "    Discount Amount : " + POSMain.discountAmount);
        });
        discount10.setOnAction(e->{
            POSMain.discountAmount = POSMain.instanceAmount*0.1;
            POSMain.instanceAmount -= POSMain.discountAmount;
            if(POSMain.instanceAmount <=0){
                POSMain.instanceAmount = 0;
            }
            POSMain.amountLabel.setText("Total : " + POSMain.instanceAmount + "    Discount Amount : " + POSMain.discountAmount);
        });
        discount20.setOnAction(e->{
            POSMain.discountAmount = POSMain.instanceAmount*0.2;

            POSMain.instanceAmount -= POSMain.discountAmount;
            if(POSMain.instanceAmount <=0){
                POSMain.instanceAmount = 0;
            }
            POSMain.amountLabel.setText("Total : " + POSMain.instanceAmount + "    Discount Amount : " + POSMain.discountAmount);
        });
        HBox discountHBox = new HBox();
        discountHBox.getChildren().addAll(discount5,discount10,discount20);
        return discountHBox;
    }


    public static HBox cashAndCard(){
        HBox newHbox = new HBox();

        card.getStyleClass().add("lower-buttons");
        cash.setOnAction(e->{
            finalReceipt.withCash(POSMain.instanceAmount);




        });
        card.setOnAction(e->{
            finalReceipt.newWindow(POSMain.removeFromCartButtons,POSMain.instanceAmount,POSMain.discountAmount);
        });
        cash.getStyleClass().add("lower-buttons");
        newHbox.getChildren().addAll(cash,card);
        return newHbox;
    }



}
