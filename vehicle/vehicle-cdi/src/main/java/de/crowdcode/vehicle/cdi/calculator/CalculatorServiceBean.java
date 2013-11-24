package de.crowdcode.vehicle.cdi.calculator;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CalculatorServiceBean implements CalculatorService {
    
    @Inject
    @FastCalculator
    private CalculatorBean calculator;
    
    /* (non-Javadoc)
     * @see de.crowdcode.vehicle.cdi.calculator.CalculatorService#sum(java.util.List)
     */
    @Override
    public int sum(List<Integer> values)
    {
        return calculator.sum(values.toArray(new Integer[]{}));
    }
    
    /* (non-Javadoc)
     * @see de.crowdcode.vehicle.cdi.calculator.CalculatorService#div(double, double)
     */
    @Override
    public double div(double a, double b)
    {
        return a/b;
    }
    

}
