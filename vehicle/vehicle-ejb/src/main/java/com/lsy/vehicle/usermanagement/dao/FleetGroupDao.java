package com.lsy.vehicle.usermanagement.dao;

import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.usermanagement.domain.FleetGroup;

public interface FleetGroupDao {

    public FleetGroup findGroup(Long id);
   
    public void create(FleetGroup group);
    
    public void update(FleetGroup group);
    
    public void remove(FleetGroup group);
    
    public FleetGroup findGroupByFleet(Fleet fleet);

    public FleetGroup findGroupByCompanyName(String string);

}
