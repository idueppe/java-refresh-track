package com.lsy.vehicle.security.web.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.lsy.vehicle.usermanagement.controller.SecurityServiceController;
import com.lsy.vehicle.usermanagement.dto.UserDto;

@ManagedBean
@SessionScoped
public class UserManager{
    
    @EJB
    private SecurityServiceController securityController;
    
    private UserDto selectedUser;
    
    public List<UserDto> getAllUsers() {
        return securityController.findAllUsers();
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
        return "/views/secure/users";
    }
    
    public String cancelAdding() {
        selectedUser = null;
        return "/views/secure/users";
    }
    

}

