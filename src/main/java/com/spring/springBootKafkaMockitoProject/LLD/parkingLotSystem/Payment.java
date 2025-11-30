package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//11
public class Payment {
    private double amount;
    private PaymentStrategy paymentStrategy;

    public Payment(double amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
       if(amount>0){
        paymentStrategy.processPayment(amount);
       } else {
        System.out.println("No payment required.");
       }
    }
}
