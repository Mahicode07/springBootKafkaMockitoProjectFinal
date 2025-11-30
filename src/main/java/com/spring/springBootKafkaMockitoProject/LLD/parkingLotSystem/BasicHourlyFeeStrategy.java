package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

import java.util.Locale;

//3
public class BasicHourlyFeeStrategy implements ParkingFeeStrategy{
    @Override
    public double calculateFee(String vehicleType, int duration, DurationType durationType) {
        return switch (vehicleType.toLowerCase()) {
            case "car" -> durationType == DurationType.HOURS ? duration * 20 : duration * 20 * 24;
            case "bike" -> durationType == DurationType.HOURS ? duration * 10 : duration * 10 * 24;
            case "truck" -> durationType == DurationType.HOURS ? duration * 30 : duration * 30 * 24;
            default -> durationType == DurationType.HOURS ? duration * 25 : duration * 25 * 24;
        };
    }
}
