package com.lsy.vehicle.domain;

import javax.persistence.*;

@Entity
public class Engine {

    @Id
    @GeneratedValue
    private Long id;
    
    private String model;
    
    @Enumerated(EnumType.STRING)
    private EngineType type;
    
    @ManyToOne
    private Manufacturer manufacturer;
    
    @Version 
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    
}
