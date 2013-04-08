package com.lsy.vehicle.usermanagement.controller.spi;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.lsy.vehicle.usermanagement.controller.SecurityServiceController;
import com.lsy.vehicle.usermanagement.converter.FleetGroupConverter;
import com.lsy.vehicle.usermanagement.converter.UserConverter;
import com.lsy.vehicle.usermanagement.domain.FleetGroup;
import com.lsy.vehicle.usermanagement.domain.Role;
import com.lsy.vehicle.usermanagement.domain.User;
import com.lsy.vehicle.usermanagement.dto.FleetGroupDto;
import com.lsy.vehicle.usermanagement.dto.UserDto;
import com.lsy.vehicle.usermanagement.service.SecurityService;

@Stateless
@Remote(SecurityServiceController.class)
public class SecurityServiceControllerBean implements SecurityServiceController {
    
    @EJB
    private SecurityService securityService;
    
    @EJB
    private UserConverter userConverter;
    
    @EJB
    private FleetGroupConverter fleetGroupConverter;

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setSurename(userDto.getSurename());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.valueOf(userDto.getRole()));
        securityService.registerUser(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userConverter.convert(securityService.findAllUser());
    }

    @Override
    public List<UserDto> findAllCustomer() {
        return userConverter.convert(securityService.findAllCustomer());
    }

    @Override
    public FleetGroupDto getGroupByCompanyName(String companyName) {
        FleetGroup group = securityService.getGroupByCompanyName(companyName);
        return fleetGroupConverter.convert(group);
    }

    @Override
    public void addUserToGroup(String companyName, String username) {
        securityService.addUserToGroup(companyName, username);
    }
}
