package com.lsy.vehicle.usermanagement.converter;

import javax.ejb.Stateless;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.usermanagement.domain.User;
import com.lsy.vehicle.usermanagement.dto.UserDto;

@Stateless
public class UserConverter extends AbstractDefaultConverter<User, UserDto>{

    @Override
    protected UserDto newTargetInstance() {
        return new UserDto();
    }

    @Override
    protected void copyProperties(User source, UserDto target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setFirstname(source.getFirstname());
        target.setSurename(source.getSurename());
        target.setRole(source.getRole().toString());
    }
    
}
