package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.VehicleDto;


public interface VehicleFleetCart {

	public void add(VehicleDto vehilceDto);
	
	public void remove(VehicleDto vehicleDto);
	
	public List<VehicleDto> listCart();
	
	public void order(String companyName);
	
}
