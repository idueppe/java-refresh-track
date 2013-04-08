package com.lsy.vehicle.usermanagement.service.spi;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.lsy.vehicle.fleet.dao.FleetDao;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.usermanagement.dao.FleetGroupDao;
import com.lsy.vehicle.usermanagement.dao.UserDao;
import com.lsy.vehicle.usermanagement.domain.FleetGroup;
import com.lsy.vehicle.usermanagement.domain.Role;
import com.lsy.vehicle.usermanagement.domain.User;
import com.lsy.vehicle.usermanagement.service.SecurityManager;

@Stateless
@Local(SecurityManager.class)
public class SecurityManagerBean implements SecurityManager {

    @EJB
    private FleetGroupDao groupDao;
    
    @EJB
    private FleetDao fleetDao;
    
    @EJB
    private UserDao userDao;

    @Override
    public void registerUser(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllCustomer() {
        return userDao.findAllOfRole(Role.CUSTOMER);
    }

    @Override
    public FleetGroup getGroupByCompanyName(String companyName) {
        FleetGroup group = groupDao.findGroupByCompanyName(companyName);
        
        if (group == null) {
            group = createFleetGroupForCompany(companyName);
        }
        
        return group;
    }

    private FleetGroup createFleetGroupForCompany(String companyName) {
        FleetGroup group = new FleetGroup();
        Fleet fleet = fleetDao.findByCompanyName(companyName);
        group.setFleet(fleet);
        groupDao.create(group);
        return group;
    }

    @Override
    public void addUserToGroup(String companyName, String username) {
        FleetGroup group = groupDao.findGroupByCompanyName(companyName);
        User user = userDao.findByUsername(username);
        group.getUsers().add(user);
        groupDao.update(group);
    }
    

}
