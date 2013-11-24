package de.crowdcode.vehicle.security.web.managers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.vehicle.security.controller.SecurityServiceController;
import de.crowdcode.vehicle.security.dto.UserDto;
import de.crowdcode.vehicle.security.filter.UserFilterParameters;

@Named
@ConversationScoped
public class UserFilterManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityServiceController securityController;

	private UserDto selectedUser = new UserDto();

	private UserFilterParameters filter = new UserFilterParameters();

	public void resetFilter()
	{
		filter = new UserFilterParameters();
	}

	public List<UserDto> getAllUsers()
	{
		System.out.println("reload list...");
		return securityController.findByFilter(filter);
	}

	public UserDto getSelectedUser()
	{
		return selectedUser;
	}

	public String startAddingNewUser()
	{
		selectedUser = new UserDto();
		return "/views/secure/adduser";
	}

	public String addUser()
	{
		securityController.registerUser(selectedUser);
		return "/views/secure/userfilter";
	}

	public String cancelAdding()
	{
		selectedUser = null;
		return "/views/secure/userfilter";
	}

	public UserFilterParameters getFilter()
	{
		return filter;
	}

}
