package com.lsy.vehicle.service;

import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Vehicle;

import java.util.List;

public interface VehicleService {

	public Vehicle getCheapestVehicle();
	
	public List<Vehicle> getVehicleByManufacture(String name);
	
	public List<Vehicle> getVehiclesByEngineType(EngineType... engineType);
	
	public Vehicle getVehicleById(Long vehicleId);
	
	public Vehicle registerVehicle(Vehicle vehicle);

    public Vehicle addVehicleToManufacturer(String string, Vehicle vehicle);

	public void delete(Vehicle vehicle);
    
}
