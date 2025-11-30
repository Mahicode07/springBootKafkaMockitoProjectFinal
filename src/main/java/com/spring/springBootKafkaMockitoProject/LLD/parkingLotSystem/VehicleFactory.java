package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

//7
public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        return switch (vehicleType.toLowerCase()) {
            case "car" -> new CarVehicle(licensePlate, vehicleType, parkingFeeStrategy);
            case "bike" -> new BikeVehicle(licensePlate, vehicleType, parkingFeeStrategy);
            default -> null;
        };
    }
}
