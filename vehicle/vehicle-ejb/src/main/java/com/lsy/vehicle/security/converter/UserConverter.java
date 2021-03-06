package com.lsy.vehicle.security.converter;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.security.domain.User;
import com.lsy.vehicle.security.dto.UserDto;

@Named
@Stateless
public class UserConverter extends AbstractDefaultConverter<User, UserDto>
{

	@Override
	protected UserDto newTargetInstance()
	{
		return new UserDto();
	}

	@Override
	protected void copyProperties(User source, UserDto target)
	{
		target.setUsername(source.getUsername());
		target.setEmail(source.getEmail());
		target.setFirstname(source.getFirstname());
		target.setSurename(source.getSurename());
		target.setRole(source.getRole().toString());
	}

}
