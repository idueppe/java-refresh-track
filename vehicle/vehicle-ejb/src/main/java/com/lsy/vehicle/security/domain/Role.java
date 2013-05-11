package com.lsy.vehicle.security.domain;

public enum Role {

    ADMIN, AGENT, CUSTOMER;
    
    public String getString() {
        return toString();
    }
    
}
