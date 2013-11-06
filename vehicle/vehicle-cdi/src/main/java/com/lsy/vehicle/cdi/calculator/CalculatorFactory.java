package com.lsy.vehicle.cdi.calculator;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

@Named
public class CalculatorFactory {

    @Produces
    public CalculatorBean newInstance()
    {
        System.out.println("---new calculator bean ---");
        return new CalculatorBean("Name des Beans ");
    }
    
    @Produces
    @FastCalculator
    public CalculatorBean newNamedInstance(InjectionPoint injectPoint)
    {
        System.err.println("field name: "+ injectPoint.getMember().getName());
        System.out.println("--- new Named Instance ---");
        return new CalculatorBean("2Bean");
    }
    
    
}
