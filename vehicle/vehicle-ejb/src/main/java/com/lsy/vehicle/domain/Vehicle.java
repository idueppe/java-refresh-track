package com.lsy.vehicle.domain;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Version;

@Entity
@NamedQueries(value={
    @NamedQuery(name="vehicleFindAll", query="SELECT v FROM Vehicle v"),
    @NamedQuery(name="vehicleFindByManufacturerName", query="SELECT v FROM Vehicle v WHERE v.manufacturer.name = :name"),
    @NamedQuery(name="vehicleByEngineType", query="SELECT v FROM Vehicle v WHERE v.engine.type = :engineType"),
    @NamedQuery(name="vehicleFindCheapest", query="SELECT v FROM Vehicle v WHERE v.nettoPrice = (SELECT min(v.nettoPrice) FROM Vehicle v)")
})

public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne()
    private Manufacturer manufacturer;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Engine engine;
    
    private String model;
    private Double nettoPrice;
    private Date constructionDate;
    
    @Embedded
    @ElementCollection
    @OrderColumn
    @AttributeOverrides({
        @AttributeOverride(name="name", column=@Column(name="seat_name")),
        @AttributeOverride(name="type", column=@Column(name="seat_type"))
    })
    private List<Seat> seat;

    private int kilometres;

    private URL smallImageURL;
    private URL normalImageURL;
    
    @Version 
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(Double nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public Date getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }

    public int getKilometres() {
        return kilometres;
    }

    public void setKilometres(int kilometres) {
        this.kilometres = kilometres;
    }

    public URL getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(URL smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public URL getNormalImageURL() {
        return normalImageURL;
    }

    public void setNormalImageURL(URL normalImageURL) {
        this.normalImageURL = normalImageURL;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
