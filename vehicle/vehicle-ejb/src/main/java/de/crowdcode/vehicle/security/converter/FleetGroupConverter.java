package de.crowdcode.vehicle.security.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.commons.converter.AbstractDefaultConverter;
import de.crowdcode.vehicle.security.domain.FleetGroup;
import de.crowdcode.vehicle.security.dto.FleetGroupDto;

@Named @Stateless
public class FleetGroupConverter extends AbstractDefaultConverter<FleetGroup, FleetGroupDto>
{

	@Inject
	private UserConverter userConverter;

	@Override
	protected FleetGroupDto newTargetInstance()
	{
		return new FleetGroupDto();
	}

	@Override
	protected void copyProperties(FleetGroup source, FleetGroupDto target)
	{
		target.setCompanyName(source.getFleet().getCompanyName());
		target.setUsers(userConverter.convert(source.getUsers()));
	}

}
