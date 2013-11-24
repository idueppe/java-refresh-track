package de.crowdcode.vehicle.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue("V")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Vendor extends Manufacturer {
    
    @Column
    private String vendorKey;

    public String getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(String vendorKey) {
        this.vendorKey = vendorKey;
    }

}
