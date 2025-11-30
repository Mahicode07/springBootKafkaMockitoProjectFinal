package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//6
public class BikeVehicle extends Vehicle{
    public BikeVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy parkingFeeStrategy) {
        super(licensePlate, "Bike", parkingFeeStrategy);
    }
}
