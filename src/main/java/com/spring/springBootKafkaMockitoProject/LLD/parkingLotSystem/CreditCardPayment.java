package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//10
public class CreditCardPayment implements PaymentStrategy{
    public CreditCardPayment(double carfee) {
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
