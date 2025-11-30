package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//9
public class CashPayment implements PaymentStrategy {


    public CashPayment(double carfee) {

    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Cash.");
    }
}
