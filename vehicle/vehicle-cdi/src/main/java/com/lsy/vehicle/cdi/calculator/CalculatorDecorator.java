package com.lsy.vehicle.cdi.calculator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.inject.Named;
@Named
@Decorator
public abstract class CalculatorDecorator implements CalculatorService {

    @Inject
    @Delegate
    private CalculatorServiceBean service;
    
    @Override
    public double div(double a, double b) {
        double result = service.div(a, b);
        System.out.println(" "+a+" / "+b+ "="+result);
        return result;
    }

}
