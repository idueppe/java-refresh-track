package com.lsy.vehicle.dao.spi.jpa;

import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.lsy.vehicle.dao.VehicleDao;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Vehicle;

@Stateless
@Local(VehicleDao.class)
@Alternative
public class NullVehicleDao implements VehicleDao {

	@Override
	public List<Vehicle> findAll() {
		return Collections.emptyList();
	}

	@Override
	public Vehicle find(Long id) {
		return null;
	}

	@Override
	public void create(Vehicle entity) {
	}

	@Override
	public void delete(Vehicle entity) {
	}

	@Override
	public Vehicle update(Vehicle entity) {
		return null;
	}

	@Override
	public List<Vehicle> findVehicleByManufacturer(String name) {
		return Collections.emptyList();
	}

	@Override
	public Vehicle findCheapestVehicle() {
		return null;
	}

	@Override
	public List<Vehicle> findVehiclesByEngineType(EngineType engineType) {
		return Collections.emptyList();
	}

}
