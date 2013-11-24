package de.crowdcode.vehicle.fleet.controller.spi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.vehicle.domain.Vehicle;
import de.crowdcode.vehicle.fleet.controller.VehicleFleetController;
import de.crowdcode.vehicle.fleet.controller.VehicleFleetControllerRemote;
import de.crowdcode.vehicle.fleet.converter.FleetVehicleDtoConverter;
import de.crowdcode.vehicle.fleet.domain.Fleet;
import de.crowdcode.vehicle.fleet.dto.FleetVehicleDto;
import de.crowdcode.vehicle.fleet.service.FleetService;
import de.crowdcode.vehicle.service.VehicleService;

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
