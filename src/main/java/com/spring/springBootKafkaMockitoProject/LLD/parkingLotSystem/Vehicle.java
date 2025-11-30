package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

import lombok.Data;

//4
@Data
public abstract class Vehicle {
    private final String licensePlate;
    private final String vehicleType;
    private ParkingFeeStrategy parkingFeeStrategy;

    public Vehicle(String licensePlate, String vehicleType, ParkingFeeStrategy parkingFeeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public double calculateFee(int duration, DurationType durationType) {
        return parkingFeeStrategy.calculateFee(vehicleType,duration, durationType);
    }

}
