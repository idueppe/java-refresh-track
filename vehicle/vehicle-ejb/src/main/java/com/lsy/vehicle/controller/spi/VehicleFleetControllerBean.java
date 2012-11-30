package com.lsy.vehicle.controller.spi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.lsy.vehicle.controller.VehicleFleetController;
import com.lsy.vehicle.converter.FleetVehicleDtoConverter;
import com.lsy.vehicle.domain.Fleet;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.service.FleetService;
import com.lsy.vehicle.service.VehicleService;

@Stateless
@Remote(VehicleFleetController.class)
public class VehicleFleetControllerBean implements VehicleFleetController {
	
	@EJB
	private FleetService fleetService;
	
	@EJB
	private VehicleService vehicleService;
	
	@EJB
	private FleetVehicleDtoConverter fleetVehicleDtoConverter;

	@Override
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName) {
		Fleet fleet = fleetService.findFleetByName(companyName);
		return fleetVehicleDtoConverter.convert(fleet.getVehicles());
	}

	@Override
	public void addVehicles(String companyName, List<VehicleDto> vehicleList) {

		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		for (VehicleDto dto : vehicleList) {
			Vehicle vehicle = vehicleService.getVehicleById(dto.getId());
			vehicles.add(vehicle);
		}
		
		fleetService.addVehicles(companyName, vehicles);
	}

}
