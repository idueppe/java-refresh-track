package com.lsy.vehicle.dao.spi.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Specializes;

import com.lsy.vehicle.dao.VehicleDao;
import com.lsy.vehicle.domain.Vehicle;

@Stateless
@Specializes
@Local(VehicleDao.class)
public class VehicleVerySpecializedDao extends VehicleJpaConstantPriceDao {

	@Override
	public void create(Vehicle vehicle) {
		vehicle.setModel("="+vehicle.getModel()+"=");
		vehicle.setNettoPrice(-1.0);
		super.create(vehicle);
	}
}
