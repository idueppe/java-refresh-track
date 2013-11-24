package com.lsy.vehicle.fleet.controller.spi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.controller.VehicleFleetController;
import com.lsy.vehicle.fleet.controller.VehicleFleetControllerRemote;
import com.lsy.vehicle.fleet.converter.FleetVehicleDtoConverter;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.fleet.dto.FleetVehicleDto;
import com.lsy.vehicle.fleet.service.FleetService;
import com.lsy.vehicle.service.VehicleService;

@Named
@Stateless
@Local(VehicleFleetController.class)
@Remote(VehicleFleetControllerRemote.class)
public class VehicleFleetControllerBean implements VehicleFleetController, VehicleFleetControllerRemote
{

	@Inject
	private FleetService fleetService;

	@Inject
	private VehicleService vehicleService;

	@Inject
	private FleetVehicleDtoConverter fleetVehicleDtoConverter;

	@Override
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName)
	{
		Fleet fleet = fleetService.findFleetByName(companyName);
		return fleetVehicleDtoConverter.convert(fleet.getVehicles());
	}

	@Override
	public void addVehicles(String companyName, List<FleetVehicleDto> vehicleList)
	{

		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		for (FleetVehicleDto dto : vehicleList)
		{
			Vehicle vehicle = vehicleService.getVehicleById(dto.getVehicleId());
			vehicles.add(vehicle);
		}

		fleetService.addVehicles(companyName, vehicles);
	}

	@Override
	public List<String> allCompanyNames()
	{
		return fleetService.allCompanyNames();
	}

	@Override
	public void deleteVehicle(String companyName, FleetVehicleDto vehicleDto)
	{
		Vehicle vehicle = vehicleService.getVehicleById(vehicleDto.getVehicleId());
		fleetService.deleteVehicle(companyName, vehicle);
	}

}
