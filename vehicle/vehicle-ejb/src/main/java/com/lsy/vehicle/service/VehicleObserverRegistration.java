package com.lsy.vehicle.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.lsy.vehicle.domain.Vehicle;

@Singleton
public class VehicleObserverRegistration {
    
    private List<VehicleObserver> observers = new ArrayList<>();
    
    @Lock(LockType.WRITE)
    public void addObserver(VehicleObserver observer) {
        observers.add(observer);
    }
    
    @Lock(LockType.WRITE)
    public void deleteObserver(VehicleObserver observer) {
        observers.remove(observer);
    }
    
    @Lock(LockType.READ)
    public void notifyDeletingVehicle(final Vehicle vehicle) {
        for(VehicleObserver observer : observers) {
            observer.onVehicleDelete(vehicle);
        }
    }

}
