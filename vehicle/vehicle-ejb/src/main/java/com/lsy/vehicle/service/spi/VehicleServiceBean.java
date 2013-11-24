package com.lsy.vehicle.service.spi;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.dao.ManufacturerDao;
import com.lsy.vehicle.dao.VehicleDao;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.service.VehicleObserverRegistration;
import com.lsy.vehicle.service.VehicleService;

@Named
@Stateless
@Local(VehicleService.class)
public class VehicleServiceBean implements VehicleService
{

	@Inject
	private VehicleDao vehicleDao;

	@Inject
	private ManufacturerDao manufacturerDao;

	@Inject
	private VehicleObserverRegistration observerRegistration;

	@Override
	public Vehicle getCheapestVehicle()
	{
		return vehicleDao.findCheapestVehicle();
	}

	@Override
	public List<Vehicle> getVehicleByManufacture(String name)
	{
		return vehicleDao.findVehicleByManufacturer(name);
	}

	@Override
	public List<Vehicle> getVehiclesByEngineType(EngineType... engineType)
	{
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		for (EngineType type : engineType)
		{
			vehicles.addAll(vehicleDao.findVehiclesByEngineType(type));
		}

		return vehicles;
	}

	@Override
	public Vehicle registerVehicle(Vehicle vehicle)
	{
		vehicleDao.create(vehicle);
		return vehicle;
	}

	@Override
	public Vehicle addVehicleToManufacturer(String manufacturerName, Vehicle vehicle)
	{
		Manufacturer manufacturer = manufacturerDao.findManufacturerByName(manufacturerName);
		if (vehicle.getId() != null)
		{
			vehicle = vehicleDao.update(vehicle);
		}
		manufacturer.addVehicle(vehicle);
		return vehicle;
	}

	@Override
	public Vehicle getVehicleById(Long vehicleId)
	{
		return vehicleDao.find(vehicleId);
	}

	@Override
	public void delete(Vehicle vehicle)
	{
		observerRegistration.notifyDeletingVehicle(vehicle);
		vehicleDao.delete(vehicle);
	}

}
