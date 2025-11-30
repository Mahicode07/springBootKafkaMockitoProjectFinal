package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

import lombok.Data;

//12
@Data
public class ParkingSpot {
    private int spotNumber;
    private String spotType; // e.g., "Compact", "Large", "Handicapped"
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, String spotType, boolean isOccupied) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = isOccupied;
    }



    // method to park a vehicle
    public void parkVehicle (Vehicle vehicle) {
        if(isOccupied){
            throw new IllegalStateException("Parking spot is already occupied");

        }
        if(canParkVehicle(vehicle)){
            this.vehicle = vehicle;
            this.isOccupied = true;
        } else {
            throw new IllegalArgumentException("Vehicle type not allowed in this spot");
        }

    }

    private boolean canParkVehicle(Vehicle vehicle) {
        // Implement logic to check if the vehicle can park in this spot based on spotType and vehicleType
        // For simplicity, let's assume all vehicle types can park in all spot types
        return true;
    }

    // method to remove a vehicle
    public void vacate() {
        if (!isOccupied) {
            throw new IllegalStateException("Parking spot is already empty");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

}
