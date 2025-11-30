package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//5
public class CarVehicle extends Vehicle{
    public CarVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy parkingFeeStrategy) {
        super(licensePlate, "Car", parkingFeeStrategy);
    }
}
