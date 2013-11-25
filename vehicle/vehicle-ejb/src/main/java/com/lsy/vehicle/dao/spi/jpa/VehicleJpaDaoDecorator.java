package com.lsy.vehicle.dao.spi.jpa;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.lsy.vehicle.dao.VehicleDao;
import com.lsy.vehicle.domain.Vehicle;

@Decorator
public abstract class VehicleJpaDaoDecorator implements VehicleDao {

	private static final Logger log = Logger.getLogger(VehicleJpaDaoDecorator.class);
	
	@Inject
	@Delegate
	private VehicleDao delegate;

	@Override
	public void create(Vehicle vehicle) {
		log.info("Set vehicle "+vehicle.getModel());
		vehicle.setNettoPrice(-1.0);
		delegate.create(vehicle);
	}
	
	
}
