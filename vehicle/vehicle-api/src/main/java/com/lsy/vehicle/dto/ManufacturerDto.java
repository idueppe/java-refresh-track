package com.lsy.vehicle.dto;

import java.io.Serializable;
import java.util.List;

public class ManufacturerDto implements Serializable{

    private static final long serialVersionUID = -2150446201518818741L;

    private Long id;
    private String name;
    private List<VehicleDto> vehicles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

}
