package com.lsy.vehicle.security.web.managers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.dto.UserDto;

@Named
@SessionScoped
public class UserManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityServiceController securityController;

	private UserDto selectedUser;

	private UserDto filter = new UserDto();

	public List<UserDto> getAllUsers()
	{
		return securityController.findByFilter(wild(filter.getUsername()), wild(filter.getEmail()),
				wild(filter.getFirstname()), wild(filter.getSurename()), filter.getRole());
	}

	private String wild(String value)
	{
		if (StringUtils.isNotBlank(value))
		{
			return "%" + value + "%";
		} else
		{
			return value;
		}
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
		return "/views/secure/users";
	}

	public String cancelAdding()
	{
		selectedUser = null;
		return "/views/secure/users";
	}

	public UserDto getFilter()
	{
		return filter;
	}

	public void setFilter(UserDto filter)
	{
		this.filter = filter;
	}

	public void setSecurityController(SecurityServiceController securityController)
	{
		this.securityController = securityController;
	}
}
