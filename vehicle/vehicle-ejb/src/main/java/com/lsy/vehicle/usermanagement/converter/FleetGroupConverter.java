package com.lsy.vehicle.usermanagement.converter;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.usermanagement.domain.FleetGroup;
import com.lsy.vehicle.usermanagement.dto.FleetGroupDto;

@Stateless
public class FleetGroupConverter extends AbstractDefaultConverter<FleetGroup, FleetGroupDto>{
    
    @EJB
    private UserConverter userConverter;

    @Override
    protected FleetGroupDto newTargetInstance() {
        return new FleetGroupDto();
    }

    @Override
    protected void copyProperties(FleetGroup source, FleetGroupDto target) {
        target.setCompanyName(source.getFleet().getCompanyName());
        target.setUsers(userConverter.convert(source.getUsers()));
    }
    
}
