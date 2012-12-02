package de.crowdcode.training;

import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assert.assertEquals;

public class CalculatorEjbIT {

    private static final String JNDI_CALCULATOR_BEAN = "vehicle-ear/CalculatorBean/remote";

    @Test
    public void testDeployedCalculatorBean() throws NamingException {
        Calculator calculator = (Calculator) JNDI.lookup(JNDI.JBOSS_ENV, JNDI_CALCULATOR_BEAN);
        
        Double multiply = calculator.multiply(1.0, 2.0, 3.0, 4.0);
        assertEquals("The bean doesn't calculate correctly", 1.0 * 2.0 * 3.0 * 4.0, multiply, 0.00001);
    }
}
