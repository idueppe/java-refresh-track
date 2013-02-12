package com.lsy.vehicle.service;

import java.util.List;

import com.lsy.vehicle.domain.Fleet;
import com.lsy.vehicle.domain.Vehicle;

public interface FleetService extends VehicleObserver {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	Fleet findFleetByName(String companyName);

    List<String> allCompanyNames();
    
    void deleteVehicle(String companyName, Vehicle vehicle);

}
