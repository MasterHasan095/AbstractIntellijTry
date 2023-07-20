package com.example.himtortons;

public class paymentMethod {
    private double amountOfCashEntered;
    private double amountToBePaid;
    private double amountToBeReturned;

    //Getter and Setter methods

    public double getAmountToBeReturned() {
        return amountToBeReturned;
    }

    public void setAmountToBeReturned(double amountToBeReturned) {
        this.amountToBeReturned = amountToBeReturned;
    }

    public double getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(double amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public double getAmountOfCashEntered() {
        return amountOfCashEntered;
    }

    public void setAmountOfCashEntered(double amountOfCashEntered) {
        this.amountOfCashEntered = amountOfCashEntered;
    }

    boolean moneyEnoughCheck(double amountOfCashEntered, double amountToBePaid){
        if (amountOfCashEntered <amountToBePaid){
            return false;
        }else {
            return true;
        }
    }

    double cashReturnCheck(double amountOfCashEntered, double amountToBePaid){
        return amountOfCashEntered - amountToBePaid;
    }
    //Constructors

    public paymentMethod(String paymentType, double amountOfCashEntered,double amountToBePaid){
        this.amountOfCashEntered = amountOfCashEntered;
        this.amountToBePaid = amountToBePaid;
        if(paymentType == "CASH"){
            if(moneyEnoughCheck(amountOfCashEntered,amountToBePaid)){
                System.out.println(cashReturnCheck(amountOfCashEntered,amountToBePaid));
            }else{
                System.out.println("Not enough Cash");
            }
        } else if (paymentType =="CARD") {
            System.out.println("We still need to implement card functionalities");
        }
    }
}
