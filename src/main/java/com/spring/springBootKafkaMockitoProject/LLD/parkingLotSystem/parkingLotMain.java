package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//14
public class parkingLotMain {
    public static void main(String[] args) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new ParkingSpot(1, "Car",false));
        parkingSpots.add(new ParkingSpot(2, "Bike",false));
        ParkingLot parkingLot = new ParkingLot(parkingSpots);

        //create fee strategy
        ParkingFeeStrategy parkingFeeStrategy = new BasicHourlyFeeStrategy();
        //create vehicle
        Vehicle car = new CarVehicle("KA-01-HH-1234", "Car", parkingFeeStrategy);
        Vehicle Bike = new BikeVehicle("KA-01-HH-9999", "Bike", parkingFeeStrategy);

        //park vehicle
        ParkingSpot carSpot = parkingLot.parkVehicle(car);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(Bike);

        Scanner scanner = new Scanner(System.in);
        System.out.println("select your payment method: 1. Cash 2. Card");
        int paymentMethod = scanner.nextInt();
        //process payment and unpark vehicle
        if (carSpot != null) {
            double carFee = car.calculateFee(2, DurationType.HOURS);
            PaymentStrategy carPaymentStrategy = getPaymentStrategy(paymentMethod, carFee);
            carPaymentStrategy.processPayment(carFee);
            parkingLot.vacateSpot(carSpot,car);
        }

        //same for bike

        scanner.close();


    }

    private static PaymentStrategy getPaymentStrategy(int paymentMethod,double carfee) {
        switch (paymentMethod){
            case 1 -> {
                return new CashPayment(carfee);
            }
            case 2 -> {
                return new CreditCardPayment(carfee);
            }
            default -> System.out.println("Invalid payment method selected.");

        }
        return null;
    }


}
