package com.lsy.vehicle.web.managers;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.controller.VehicleFleetController;
import com.lsy.vehicle.dto.FleetVehicleDto;

@ManagedBean
@SessionScoped
public class CompanyManager {

    @EJB
    private VehicleFleetController fleetController;
    
    private String selectedCompany;

    
    public String getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(String selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    
    public List<String> getCompanies() {
        return fleetController.allCompanyNames();
    }
    
    public List<FleetVehicleDto> getFleet() {
        if (StringUtils.isNotBlank(selectedCompany)) {
            return fleetController.getVehicleFleetByName(selectedCompany);
        } else {
            return Collections.emptyList();
        }
    }
    
}
