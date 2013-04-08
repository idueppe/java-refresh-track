package com.lsy.vehicle.fleet.controller;

import java.util.List;

import com.lsy.vehicle.fleet.dto.FleetVehicleDto;


public interface VehicleFleetCart {

	public void add(FleetVehicleDto vehilceDto);
	
	public void remove(FleetVehicleDto vehicleDto);
	
	public List<FleetVehicleDto> listCart();
	
	public void order(String companyName);

    public void close();
	
}
