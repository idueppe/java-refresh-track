package com.lsy.vehicle.controller;

import com.lsy.vehicle.dto.VehicleDto;

import java.util.List;


public interface VehicleFleetCart {

	public void add(VehicleDto vehilceDto);
	
	public void remove(VehicleDto vehicleDto);
	
	public List<VehicleDto> listCart();
	
	public void order(String companyName);
	
}
