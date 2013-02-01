package com.lsy.vehicle.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQueries(
	value = {
	     @NamedQuery(name=Fleet.FIND_BY_COMPANY_NAME, query="SELECT f FROM Fleet f WHERE f.companyName = :companyName"),
	     @NamedQuery(name=Fleet.FIND_ALL, query="SELECT f FROM Fleet f"),
	     @NamedQuery(name=Fleet.FIND_ALL_COMPANY_NAMES, query="SELECT f.companyName FROM Fleet f")
	}
)
public class Fleet {
    
    public static final String FIND_BY_COMPANY_NAME = "Fleet.findByCompanyName";
    public static final String FIND_ALL = "Fleet.findAll";
    public static final String FIND_ALL_COMPANY_NAMES = "Fleet.findAllCompanyNames";
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String companyName;
	
	@ManyToMany()
	private List<Vehicle> vehicles;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Vehicle> getVehicles() {
		if (vehicles == null) {
			vehicles = new ArrayList<Vehicle>();
		}
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


}
