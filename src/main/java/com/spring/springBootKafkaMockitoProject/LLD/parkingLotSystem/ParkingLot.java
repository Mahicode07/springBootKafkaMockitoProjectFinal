package com.spring.springBootKafkaMockitoProject.LLD.parkingLotSystem;

import lombok.Getter;

import java.util.List;

//13

@Getter
public class ParkingLot {
    List<ParkingSpot> parkingSpots;

    public ParkingLot(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied() && spot.getSpotType().equals(vehicle.getVehicleType())) {
                return spot;
            }
        }
        return null;
    }

    // method to park vehicle
    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle);
        if (spot != null) {
            spot.parkVehicle(vehicle);
            System.out.println("vehicle parked at spot: " + spot.getSpotNumber());
            return spot;
        }
        System.out.println("no available spot for vehicle type: " + vehicle.getVehicleType());
        return null;
    }

    // method to unpark vehicle
    public void vacateSpot(ParkingSpot spot,Vehicle vehicle) {
            if (spot != null && spot.isOccupied() && spot.getVehicle().equals(vehicle)) {
                spot.vacate();
                System.out.println("vehicle vacated from spot: " + spot.getSpotNumber());
            } else {
                System.out.println("invalid spot or vehicle");
            }
    }

}
