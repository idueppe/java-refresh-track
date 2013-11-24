package de.crowdcode.vehicle.fleet.controller.spi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.vehicle.fleet.controller.VehicleFleetCart;
import de.crowdcode.vehicle.fleet.controller.VehicleFleetCartRemote;
import de.crowdcode.vehicle.fleet.controller.VehicleFleetController;
import de.crowdcode.vehicle.fleet.dto.FleetVehicleDto;

@Named
@Stateful
@Local(VehicleFleetCart.class)
@Remote(VehicleFleetCartRemote.class)
public class VehicleFleetCartBean implements VehicleFleetCart, Serializable
{

	private static final Logger LOG = Logger.getLogger(VehicleFleetCartBean.class.getName());

	private static final long serialVersionUID = 1L;

	private List<FleetVehicleDto> vehicleList = new ArrayList<FleetVehicleDto>();

	@Inject
	private VehicleFleetController fleetController;

	@Override
	public void add(FleetVehicleDto vehicleDto)
	{
		vehicleList.add(vehicleDto);
	}

	@Override
	public void remove(FleetVehicleDto vehicleDto)
	{
		vehicleList.remove(vehicleDto);
	}

	@Override
	public List<FleetVehicleDto> listCart()
	{
		return vehicleList;
	}

	@Override
	public void order(String companyName)
	{
		fleetController.addVehicles(companyName, vehicleList);
	}

	@Override
	@Remove
	public void close()
	{
		LOG.info("Closing vehicle fleet cart.");
	}
}
