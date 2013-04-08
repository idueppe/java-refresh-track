package com.lsy.vehicle.usermanagement.service;

import java.util.List;

import com.lsy.vehicle.usermanagement.domain.FleetGroup;
import com.lsy.vehicle.usermanagement.domain.User;

public interface SecurityService {
    
    public void registerUser(User user);
    
    public List<User> findAllUser();
    
    public List<User> findAllCustomer();
    
    public FleetGroup getGroupByCompanyName(String companyName);
    
    public void addUserToGroup(String companyName, String username);

}
