package com.lsy.vehicle.security.web.managers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.fleet.controller.VehicleFleetController;
import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.dto.FleetGroupDto;
import com.lsy.vehicle.security.dto.UserDto;

@Named
@SessionScoped
public class GroupManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityServiceController securityController;

	@Inject
	private VehicleFleetController fleetController;

	private String selectedCompany;

	private FleetGroupDto selectedGroup;

	public List<String> getCompanies()
	{
		return fleetController.allCompanyNames();
	}

	public List<UserDto> getAllCustomers()
	{
		return securityController.findAllCustomersNotMemberOf(selectedCompany);
	}

	public String getSelectedCompany()
	{
		return selectedCompany;
	}

	public void setSelectedCompany(String selectedCompany)
	{
		this.selectedCompany = selectedCompany;
		if (selectedCompany != null && !selectedCompany.isEmpty())
		{
			selectedGroup = securityController.getGroupByCompanyName(selectedCompany);
		} else
		{
			selectedGroup = null;
		}
	}

	public FleetGroupDto getSelectedGroup()
	{
		return selectedGroup;
	}

	public void setSelectedGroup(FleetGroupDto selectedGroup)
	{
		this.selectedGroup = selectedGroup;
	}

	public String save()
	{
		for (UserDto user : selectedGroup.getUsers())
		{
			securityController.addUserToGroup(selectedCompany, user.getUsername());
		}
		return "/views/secure/groups";
	}

}
