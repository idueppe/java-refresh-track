package com.lsy.vehicle.fleet.web.managers;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.fleet.controller.VehicleFleetController;
import com.lsy.vehicle.fleet.dto.FleetVehicleDto;

@Named
@SessionScoped
public class CompanyManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Inject
	private VehicleFleetController fleetController;

	private String selectedCompany;

	public String getSelectedCompany()
	{
		return selectedCompany;
	}

	public void setSelectedCompany(String selectedCompany)
	{
		this.selectedCompany = selectedCompany;
	}

	public List<String> getCompanies()
	{
		return fleetController.allCompanyNames();
	}

	public List<FleetVehicleDto> getFleet()
	{
		if (StringUtils.isNotBlank(selectedCompany))
		{
			return fleetController.getVehicleFleetByName(selectedCompany);
		} else
		{
			return Collections.emptyList();
		}
	}

	public String deleteVehicle(FleetVehicleDto vehicle)
	{
		fleetController.deleteVehicle(selectedCompany, vehicle);
		return "";
	}

}
