package de.crowdcode.vehicle.controller;

import de.crowdcode.vehicle.dto.VehicleDto;

import java.util.List;

public interface VehicleController {

    public List<VehicleDto> findVehicleByManufacturer(String manufacturerName);
    
    public void saveOrUpdateVehicle(VehicleDto vehicle);
    
    public void deleteVehicle(VehicleDto vehicle);

    public VehicleDto getVehicle(Long id);
    
}
