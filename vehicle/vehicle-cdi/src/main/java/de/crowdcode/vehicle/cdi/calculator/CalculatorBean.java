package de.crowdcode.vehicle.cdi.calculator;

import javax.inject.Named;

@Named
public class CalculatorBean {
    
    private String name;
    
    public CalculatorBean(String name)
    {
        this.name = name;
    }
    
    public int sum(Integer... values)
    {
        System.out.println(name);
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

}
