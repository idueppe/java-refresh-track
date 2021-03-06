package com.lsy.vehicle.controller.spi;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.controller.VehicleControllerRemote;
import com.lsy.vehicle.converter.VehicleConverter;
import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.log.service.ApplicationLogService;
import com.lsy.vehicle.service.VehicleService;

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
