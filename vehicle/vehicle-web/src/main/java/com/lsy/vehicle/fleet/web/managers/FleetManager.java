package com.lsy.vehicle.fleet.web.managers;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.fleet.controller.VehicleFleetCart;
import com.lsy.vehicle.fleet.dto.FleetVehicleDto;

@Named
@SessionScoped
public class FleetManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(FleetManager.class.getName());

	@Inject
	private ManufacturerController manufacturerController;

	@Inject
	private VehicleController vehicleController;

	@Inject
	private VehicleFleetCart fleetCart;

	private ManufacturerDto selectedManufacturer;

	private VehicleDto selectedVehicle;

	private String companyName;

	private boolean addingToExistingCompany;

	public List<ManufacturerDto> getAvailableManufacturers()
	{
		return manufacturerController.allManufactures();
	}

	public List<VehicleDto> getAvailableVehicles()
	{
		if (selectedManufacturer != null)
		{
			return selectedManufacturer.getVehicles();
		} else
		{
			return Collections.emptyList();
		}
	}

	public List<FleetVehicleDto> getFleet()
	{
		return fleetCart.listCart();
	}

	public String addVehicle()
	{
		LOG.info("Adding selected vehicle " + selectedVehicle.getModelName() + " to fleet.");

		FleetVehicleDto fleetVehicle = new FleetVehicleDto();
		fleetVehicle.setVehicleId(selectedVehicle.getId());
		fleetVehicle.setVehicleModel(selectedVehicle.getModelName());
		fleetVehicle.setOrderDate(new Date());
		fleetVehicle.setManufacturerName(selectedVehicle.getManufacturerName());
		fleetVehicle.setConstructionDate(selectedVehicle.getConstructionDate());

		fleetCart.add(fleetVehicle);

		selectedVehicle = null;
		selectedManufacturer = null;

		return null;
	}

	public String delete(FleetVehicleDto vehicle)
	{
		LOG.info("Deleting vehicle " + vehicle.getVehicleModel() + " from car.");
		fleetCart.remove(vehicle);

		return null;
	}

	@PostConstruct
	public void postConstruct()
	{
		LOG.info("Opening new fleet cart");
	}

	@PreDestroy
	public void preDestroy()
	{
		LOG.info("Closing fleet cart");
		fleetCart.close();
		// cause misleading log statement see
		// https://issues.jboss.org/browse/AS7-5077
		fleetCart = null;
	}

	public String addVehicleFor(String companyName)
	{
		this.companyName = companyName;
		addingToExistingCompany = true;
		return "/views/fleet";
	}

	public String order()
	{
		fleetCart.order(companyName);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getSessionMap().put("fleetManager", null);

		// Different variations of removing a session scoped bean

		// facesContext.getApplication().createValueBinding("#{fleetManager}").setValue(facesContext,
		// null);

		// ExpressionFactory expressionFactory =
		// facesContext.getApplication().getExpressionFactory();
		// ValueExpression valueExpression =
		// expressionFactory.createValueExpression("#{fleetManager}",
		// FleetManager.class);
		// valueExpression.setValue(facesContext.getELContext(), null);

		return "/views/companies";
	}

	public void setSelectedManufacturer(ManufacturerDto selectedManufacturer)
	{
		LOG.info("Manufacturer is selected " + selectedManufacturer);
		if (selectedManufacturer != null)
		{
			this.selectedManufacturer = manufacturerController.byName(selectedManufacturer.getName());
		} else
		{
			this.selectedManufacturer = null;
		}
		this.selectedVehicle = null;
	}

	public ManufacturerDto getSelectedManufacturer()
	{
		return selectedManufacturer;
	}

	public VehicleDto getSelectedVehicle()
	{
		return selectedVehicle;
	}

	public void setSelectedVehicle(VehicleDto selectedVehicle)
	{
		LOG.info("Vehicle is selected " + selectedVehicle);
		if (selectedVehicle != null)
		{
			this.selectedVehicle = vehicleController.getVehicle(selectedVehicle.getId());
		} else
		{
			this.selectedVehicle = null;
		}
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public boolean isAddingToExistingCompany()
	{
		return addingToExistingCompany;
	}

}
