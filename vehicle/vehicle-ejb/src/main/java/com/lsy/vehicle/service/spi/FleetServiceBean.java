package com.lsy.vehicle.service.spi;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.lsy.vehicle.dao.FleetDao;
import com.lsy.vehicle.domain.Fleet;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.service.FleetService;

@Stateless
@Local(FleetService.class)
public class FleetServiceBean implements FleetService {
	
	@EJB
	private FleetDao fleetDao;

	@Override
	public void addVehicles(String companyName, List<Vehicle> vehicleList) {
		Fleet fleet = fleetDao.findByCompanyName(companyName);
		if (fleet == null) {
			fleet = new Fleet();
			fleet.setCompanyName(companyName);
			fleetDao.create(fleet);
		}
		fleet.getVehicles().addAll(vehicleList);
	}

	@Override
	public Fleet findFleetByName(String companyName) {
		return fleetDao.findByCompanyName(companyName);
	}

}
