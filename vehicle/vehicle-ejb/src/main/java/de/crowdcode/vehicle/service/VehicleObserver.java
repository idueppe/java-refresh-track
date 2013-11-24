package de.crowdcode.vehicle.service;

import de.crowdcode.vehicle.domain.Vehicle;

public interface VehicleObserver {

    public void onVehicleDelete(Vehicle vehicle);
    
}
