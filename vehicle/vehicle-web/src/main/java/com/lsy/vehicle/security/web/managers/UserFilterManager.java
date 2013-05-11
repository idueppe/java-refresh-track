package com.lsy.vehicle.security.web.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.dto.UserDto;
import com.lsy.vehicle.security.filter.UserFilterParameters;

@ManagedBean
@SessionScoped
public class UserFilterManager{
    
    @EJB
    private SecurityServiceController securityController;
    
    private UserDto selectedUser;
    
    private UserFilterParameters filter = new UserFilterParameters();
    
    public void resetFilter() {
        filter = new UserFilterParameters();
    }
    
    public List<UserDto> getAllUsers() {
        System.out.println("reload list...");
        return securityController.findByFilter(filter);
    }
    
    public UserDto getSelectedUser() {
        return selectedUser;
    }
    
    public String startAddingNewUser() {
        selectedUser = new UserDto();
        return "/views/secure/adduser";
    }

    public String addUser() {
        securityController.registerUser(selectedUser);
        return "/views/secure/userfilter";
    }
    
    public String cancelAdding() {
        selectedUser = null;
        return "/views/secure/userfilter";
    }

    public UserFilterParameters getFilter() {
        return filter;
    }

}

