package com.lsy.vehicle.cdi.calculator;

import java.util.List;

public interface CalculatorService {

    public abstract int sum(List<Integer> values);

    public abstract double div(double a, double b);

}