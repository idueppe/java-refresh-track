package de.crowdcode.vehicle.controller.spi;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.vehicle.controller.VehicleController;
import de.crowdcode.vehicle.controller.VehicleControllerRemote;
import de.crowdcode.vehicle.converter.VehicleConverter;
import de.crowdcode.vehicle.domain.Engine;
import de.crowdcode.vehicle.domain.EngineType;
import de.crowdcode.vehicle.domain.Vehicle;
import de.crowdcode.vehicle.dto.VehicleDto;
import de.crowdcode.vehicle.log.service.ApplicationLogService;
import de.crowdcode.vehicle.service.VehicleService;

@Named
@Stateless
@Local(VehicleController.class)
@Remote(VehicleControllerRemote.class)
public class VehicleControllerBean implements VehicleController, VehicleControllerRemote
{

	@Inject
	private VehicleService vehicleService;

	@Inject
	private VehicleConverter vehicleConverter;

	@Inject
	private ApplicationLogService log;

	@Override
	public List<VehicleDto> findVehicleByManufacturer(String manufacturerName)
	{
		List<Vehicle> vehicles = vehicleService.getVehicleByManufacture(manufacturerName);
		return vehicleConverter.convert(vehicles);
	}

	@Override
	public void saveOrUpdateVehicle(VehicleDto vehicleDto)
	{
		log.log("saving or updating a vehicle...");
		if (vehicleDto.getId() != null)
		{
			updateExistingVehicle(vehicleDto);
		} else
		{
			createNewVehicle(vehicleDto);
		}
	}

	private void createNewVehicle(VehicleDto vehicleDto)
	{
		Vehicle vehicle;
		vehicle = new Vehicle();
		vehicle.setConstructionDate(vehicleDto.getConstructionDate());
		vehicle.setModel(vehicleDto.getModelName());

		Engine engine = new Engine();
		engine.setType(EngineType.valueOf(vehicleDto.getEngine().getEngineType()));
		vehicle.setEngine(engine);

		vehicleService.registerVehicle(vehicle);
		vehicleService.addVehicleToManufacturer(vehicleDto.getManufacturerName(), vehicle);
	}

	private void updateExistingVehicle(VehicleDto vehicleDto)
	{
		Vehicle vehicle = vehicleService.getVehicleById(vehicleDto.getId());
		vehicle.setConstructionDate(vehicleDto.getConstructionDate());
		vehicle.setModel(vehicleDto.getModelName());
	}

	@Override
	public void deleteVehicle(VehicleDto vehicle)
	{
		Vehicle vehicleEntity = vehicleService.getVehicleById(vehicle.getId());
		vehicleService.delete(vehicleEntity);
	}

	@Override
	public VehicleDto getVehicle(Long vehicleId)
	{
		return vehicleConverter.convert(vehicleService.getVehicleById(vehicleId));
	}

}
