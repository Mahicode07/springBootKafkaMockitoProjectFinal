package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//1
public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType,int duration,DurationType durationType);
}
