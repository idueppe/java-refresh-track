package de.crowdcode.vehicle.fleet.service;

import java.util.List;

import de.crowdcode.vehicle.domain.Vehicle;
import de.crowdcode.vehicle.fleet.domain.Fleet;
import de.crowdcode.vehicle.service.VehicleObserver;

public interface FleetService extends VehicleObserver {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	Fleet findFleetByName(String companyName);

    List<String> allCompanyNames();
    
    void deleteVehicle(String companyName, Vehicle vehicle);

}
