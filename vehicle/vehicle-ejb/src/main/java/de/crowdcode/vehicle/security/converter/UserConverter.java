package de.crowdcode.vehicle.security.converter;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.crowdcode.commons.converter.AbstractDefaultConverter;
import de.crowdcode.vehicle.security.domain.User;
import de.crowdcode.vehicle.security.dto.UserDto;

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
