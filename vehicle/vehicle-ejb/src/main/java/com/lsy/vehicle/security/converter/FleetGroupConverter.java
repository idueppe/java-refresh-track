package com.lsy.vehicle.security.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.dto.FleetGroupDto;

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
