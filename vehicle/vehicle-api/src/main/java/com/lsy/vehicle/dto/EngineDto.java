package com.lsy.vehicle.dto;

import java.io.Serializable;


public class EngineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long engineId;
    private String engineType;

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

}
