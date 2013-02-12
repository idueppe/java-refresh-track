package com.lsy.vehicle.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyName {

    @Column(name="companyName", unique=true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
