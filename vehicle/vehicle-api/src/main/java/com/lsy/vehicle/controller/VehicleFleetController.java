package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.FleetVehicleDto;

public interface VehicleFleetController {
	
	public List<FleetVehicleDto> getVehicleFleetByName(String name);

}
