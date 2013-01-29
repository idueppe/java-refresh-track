package com.lsy.vehicle.web.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.dto.VehicleDto;

@ManagedBean
@SessionScoped
public class FleetManager {
    
    private static final Logger LOG = Logger.getLogger(FleetManager.class.getName());

    @EJB
    private ManufacturerController manufacturerController;
    
    @EJB
    private VehicleController vehicleController;
    
    private ManufacturerDto selectedManufacturer;
    
    private VehicleDto selectedVehicle;
    
    private String companyName;
    
    private List<FleetVehicleDto> fleet = new ArrayList<>();
    
    public List<ManufacturerDto> getAvailableManufacturers()  {
        return manufacturerController.allManufactures();
    }
    
    public List<VehicleDto> getAvailableVehicles() {
        if (selectedManufacturer != null) {
            return selectedManufacturer.getVehicles();
        } else {
            return Collections.emptyList();
        }
    }
    
    public List<FleetVehicleDto> getFleet() {
        return fleet;
    }
    
    public String addVehicle() {
        LOG.info("Adding vehicle to fleet...");
        
        FleetVehicleDto fleetVehicle = new FleetVehicleDto();
        fleetVehicle.setVehicleModel(selectedVehicle.getModelName());
        fleetVehicle.setOrderDate(new Date());
        fleetVehicle.setManufacturerName(selectedManufacturer.getName());
        fleetVehicle.setConstructionDate(selectedVehicle.getConstructionDate());
        
        fleet.add(fleetVehicle);
        
        return "";
    }

    public String delete(FleetVehicleDto vehicle) {
        return null;
    }
    
    public void setSelectedManufacturer(ManufacturerDto selectedManufacturer) {
        LOG.info("Manufacturer is selected "+selectedManufacturer);
        if (selectedManufacturer != null) {
            this.selectedManufacturer = manufacturerController.byName(selectedManufacturer.getName());
        } else {
            this.selectedManufacturer = null;
        }
        this.selectedVehicle = null;
    }
    
    public ManufacturerDto getSelectedManufacturer() {
        return selectedManufacturer;
    }
    
    public VehicleDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleDto selectedVehicle) {
        LOG.info("Vehicle is selected "+selectedVehicle);
        if (selectedVehicle != null) {
            this.selectedVehicle = vehicleController.getVehicle(selectedVehicle.getId());
        } else {
            this.selectedVehicle = null;
        }
            
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    

}

