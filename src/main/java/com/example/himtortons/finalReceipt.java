package com.example.himtortons;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class finalReceipt {
    static VBox receipt = new VBox();
    static Stage newStage = new Stage();
    static Scene newScene = new Scene(receipt, 300, 400);
    static Boolean tempCheck;
    public static void newWindow(List<Button> cartList,Double total,Double discountGiven) {
        receipt.getChildren().clear();


        for (Button button : cartList){
            Label receiptLabel = new Label(button.getText());
            receipt.getChildren().add(receiptLabel);
        }

        Label totalAmount = new Label();
        totalAmount.setText("Total Amount : " + total);
        Label discountAmount = new Label();
        discountAmount.setText("Discount Provided : " + discountGiven);
        Button orderCompleted = new Button("Order Complete");
        orderCompleted.setOnAction(e->{
            File orderHistory = new File("orderHistory.txt");
            if (!orderHistory.exists()){
                System.out.println("Doesn't exist");
                try {
                    orderHistory.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                System.out.println("Exists now");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(orderHistory,true));
                    bw.write("*****ORDER***** \n");
                    for (Button button : POSMain.removeFromCartButtons){
                        bw.write(button.getText() + "\n");
                    }
                    bw.write("Total Amount : " + POSMain.instanceAmount);
                    bw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            cartList.clear();
            POSMain.removeFromCartButtons.clear();
            POSMain.orderPane.setContent(receiptGeneration.receiptGenerated(POSMain.removeFromCartButtons));
            POSMain.instanceAmount = 0;
            POSMain.discountAmount = 0;
            POSMain.amountLabel.setText("Total : " + POSMain.instanceAmount+ "    Discount Amount : " + POSMain.discountAmount);
            receipt.getChildren().clear();
            newStage.close();
        });
        receipt.getChildren().addAll(totalAmount,discountAmount,orderCompleted);


    }
    public static void withCash(Double instanceAmount){
        newStage.setTitle("Receipt");
        newStage.setScene(newScene);
        newStage.show();
        HBox hbox = new HBox();
        Label amountOfCashEnteredLabel = new Label("Amount of cash provided : ");
        amountOfCashEnteredLabel.setPrefWidth(150);
        TextField amountOfCashEntered = new TextField();
        amountOfCashEntered.setPrefWidth(150);
        Button calculate = new Button("Calculate");
        hbox.getChildren().addAll(amountOfCashEnteredLabel,amountOfCashEntered,calculate);
        receipt.getChildren().add(hbox);
        System.out.println(amountOfCashEntered.getText());
        calculate.setOnAction(e->{
            System.out.println("This works");
            Double toReturnValue = Double.parseDouble(amountOfCashEntered.getText()) - instanceAmount ;
            Label toReturnAmount = new Label();
            toReturnAmount.setText("Cash to be returned : " + String.valueOf(toReturnValue));
            receipt.getChildren().add(toReturnAmount);
        });
        Button done = new Button("Done");
        receipt.getChildren().add(done);
        done.setOnAction(e-> {
            finalReceipt.newWindow(POSMain.removeFromCartButtons, POSMain.instanceAmount, POSMain.discountAmount);
        });

    }
    public static void withCard(){
        newStage.setTitle("Receipt");
        newStage.setScene(newScene);
        newStage.show();
        Label newLabel = new Label("Please Tap the card");
        Button cardTapped = new Button("Card Tapped");
        receipt.getChildren().addAll(newLabel,cardTapped);
        cardTapped.setOnAction(e->{
            receipt.getChildren().clear();
            finalReceipt.newWindow(POSMain.removeFromCartButtons, POSMain.instanceAmount, POSMain.discountAmount);
        });
    }


}
