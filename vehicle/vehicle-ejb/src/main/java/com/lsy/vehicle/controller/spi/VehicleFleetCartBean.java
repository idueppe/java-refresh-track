package com.lsy.vehicle.controller.spi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.lsy.vehicle.controller.VehicleFleetCart;
import com.lsy.vehicle.controller.VehicleFleetController;
import com.lsy.vehicle.dto.VehicleDto;

@Stateful
@Remote(VehicleFleetCart.class)
public class VehicleFleetCartBean implements VehicleFleetCart, Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<VehicleDto> vehicleList = new ArrayList<VehicleDto>();
	
	@EJB
	private VehicleFleetController fleetController;

	@Override
	public void add(VehicleDto vehicleDto) {
		vehicleList.add(vehicleDto);
	}

	@Override
	public void remove(VehicleDto vehicleDto) {
		vehicleList.remove(vehicleDto);
	}

	@Override
	public List<VehicleDto> listCart() {
		return vehicleList;
	}

	@Override
	@Remove
	public void order(String companyName) {
		fleetController.addVehicles(companyName, vehicleList);
	}

}
