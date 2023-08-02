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
        receipt.getChildren().addAll(totalAmount,discountAmount);


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
            Double toReturnValue = instanceAmount - Double.parseDouble(amountOfCashEntered.getText());
            Label toReturnAmount = new Label();
            toReturnAmount.setText(String.valueOf(toReturnValue));
            receipt.getChildren().add(toReturnAmount);
        });
        Button done = new Button("Done");
        receipt.getChildren().add(done);
        done.setOnAction(e-> {
            finalReceipt.newWindow(POSMain.removeFromCartButtons, POSMain.instanceAmount, POSMain.discountAmount);

        });

    }


}
