package com.lsy.vehicle.service;

import java.util.List;

import com.lsy.vehicle.domain.Fleet;
import com.lsy.vehicle.domain.Vehicle;

public interface FleetService {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	Fleet findFleetByName(String companyName);

}
