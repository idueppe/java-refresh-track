package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.dto.VehicleDto;

public interface VehicleFleetController {
	
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName);

	public void addVehicles(String companyName, List<VehicleDto> vehicleList);

}
