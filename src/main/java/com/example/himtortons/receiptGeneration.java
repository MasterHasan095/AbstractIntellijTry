package com.example.himtortons;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

public class receiptGeneration {
    static VBox receipt = new VBox();
    public static void ButtonListGeneration(String orderLine){
        Button orderLineButton = new Button();
        orderLineButton.setText(orderLine);
        POSMain.removeFromCartButtons.add(orderLineButton);
    }

    public static VBox receiptGenerated(List<Button> buttonList){
        receipt.getChildren().clear();
        for (Button button : buttonList){
            button.setOnAction(e->{
                POSMain.removeFromCartButtons.remove(button);
                POSMain.instanceAmount -= POSMain.nameAndPrice.get(button.getText());
                if(POSMain.instanceAmount <=0){
                    POSMain.instanceAmount = 0;
                }
                POSMain.amountLabel.setText("Total : " + POSMain.instanceAmount);
                POSMain.orderPane.setContent(receiptGeneration.receiptGenerated(POSMain.removeFromCartButtons));

            });
            receipt.getChildren().add(button);
            button.getStyleClass().add("receipt-button");
        }
        return receipt;
    };

}
