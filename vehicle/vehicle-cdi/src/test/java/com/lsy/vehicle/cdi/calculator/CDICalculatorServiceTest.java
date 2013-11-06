package com.lsy.vehicle.cdi.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses({CalculatorFactory.class, CalculatorServiceBean.class, CalculatorDecorator.class})
public class CDICalculatorServiceTest {

    
    @Inject
    private CalculatorService service;
    
    @Inject
    @FastCalculator
    private CalculatorBean nameOfTestField;
    
    
    @Test
    public void testSum() {
        assertEquals(10,service.sum(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void testDiv() {
        assertEquals(10.0,service.div(20.0, 2.0), 0.0001);
    }
    
}
