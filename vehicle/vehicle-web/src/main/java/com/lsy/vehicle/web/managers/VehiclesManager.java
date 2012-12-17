package com.lsy.vehicle.web.managers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.EngineDto;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.dto.VehicleDto;

/**
 * @author idueppe
 * @since 1.0
 */
@ManagedBean
@SessionScoped
public class VehiclesManager {

    private static final Logger LOG = Logger.getLogger(VehiclesManager.class.getName());

    @EJB
    private VehicleController vehicleController;
    private ManufacturerDto manufacturer;
    
    private VehicleDto vehicle;

    public List<VehicleDto> getVehicles() {
        if (manufacturer != null) {
            return vehicleController.findVehicleByManufacturer(manufacturer.getName());
        } else {
            return Collections.emptyList();
        }
    }

    public String showVehicles(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
        return "/views/vehicles";
    }

    public String delete(VehicleDto vehicleDto) {
        LOG.info("----------- DELETING VEHICLE...");
        vehicleController.deleteVehicle(vehicleDto);
        // TODO Hier eine Nachricht platzieren, dass das Fahrzeug gelöscht wurde.
        return "/views/vehicles";
    }

    public String addVehicle() {
        
        vehicle.setManufacturerName(manufacturer.getName());
        vehicle.setEngine(new EngineDto());
        vehicle.getEngine().setEngineType("DIESEL");
        
        
        vehicleController.saveOrUpdateVehicle(vehicle);
        
        
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        msg.setSummary("Diese Funktion ist noch nicht implementiert.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/views/vehicles";
    }

    public String cancelAdding() {
        // TODO Hier eine Nachricht über den Abbruch absetzen.
        return "/views/vehicles";
    }
    
    public String startAddingVehicle() {
        vehicle = new VehicleDto();
        return "/views/addvehicle";
    }

    public String selectForUpdate(VehicleDto vehicle) {
        this.vehicle = vehicle;
        return "/views/addvehicle";
    }


    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
    }

}
