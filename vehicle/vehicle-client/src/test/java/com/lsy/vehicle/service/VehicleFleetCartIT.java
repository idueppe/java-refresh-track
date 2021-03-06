package com.lsy.vehicle.service;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.fleet.controller.VehicleFleetCart;
import com.lsy.vehicle.fleet.controller.VehicleFleetController;
import com.lsy.vehicle.fleet.dto.FleetVehicleDto;

import de.crowdcode.training.JNDI;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleFleetCartIT {

	private VehicleFleetCart fleetCart;

	private VehicleController vehicleController;
	
	private VehicleFleetController fleetController;

	@Before
	public void setUp() throws NamingException {
		fleetCart = (VehicleFleetCart) JNDI.lookup(JNDI.JBOSS_ENV, "vehicle-ear/VehicleFleetCartBean/remote");
//		fleetCart = (VehicleFleetCart) JNDI.lookup(JNDI.JBOSS_ENV, "vehicle-ear/VehicleFleetCartBean/remote-com.lsy.vehicle.controller.VehicleFleetCart");
		vehicleController = (VehicleController) JNDI.lookup(JNDI.JBOSS_ENV,	"vehicle-ear/VehicleControllerBean/remote");
		fleetController = (VehicleFleetController) JNDI.lookup(JNDI.JBOSS_ENV, "vehicle-ear/VehicleFleetControllerBean/remote");
	}

	@Test
	public void testFleetOrdering() {
		List<VehicleDto> vehicles = vehicleController.findVehicleByManufacturer("Buggati");

		for (VehicleDto vehicleDto : vehicles) {
		    FleetVehicleDto dto = new FleetVehicleDto();
		    dto.setVehicleId(vehicleDto.getId());
		    dto.setVehicleModel(vehicleDto.getModelName());
			fleetCart.add(dto);
		}

		assertEquals("Cart size is wrong", vehicles.size(), fleetCart.listCart().size());
		fleetCart.order("JUNIT_COMPANY_NAME");
		
		List<FleetVehicleDto> fleet = fleetController.getVehicleFleetByName("JUNIT_COMPANY_NAME");
		assertNotNull("The fleet should be never null.", fleet);
		assertFalse("The fleet should at least contain the cars above.", fleet.isEmpty());
	}

}
