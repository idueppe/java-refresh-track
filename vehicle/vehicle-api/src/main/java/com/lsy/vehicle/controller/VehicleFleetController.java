package com.lsy.vehicle.controller;

import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.dto.VehicleDto;

import java.util.List;

public interface VehicleFleetController {
	
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName);

	public void addVehicles(String companyName, List<VehicleDto> vehicleList);

}
