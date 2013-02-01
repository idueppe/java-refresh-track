package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.FleetVehicleDto;


public interface VehicleFleetCart {

	public void add(FleetVehicleDto vehilceDto);
	
	public void remove(FleetVehicleDto vehicleDto);
	
	public List<FleetVehicleDto> listCart();
	
	public void order(String companyName);

    public void close();
	
}
