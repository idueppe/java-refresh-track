package de.crowdcode.vehicle.security.domain;

public enum Role {

    ADMIN, AGENT, CUSTOMER;
    
    public String getString() {
        return toString();
    }
    
}
