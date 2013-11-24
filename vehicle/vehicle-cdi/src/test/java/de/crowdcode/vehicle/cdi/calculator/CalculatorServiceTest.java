package de.crowdcode.vehicle.cdi.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.enterprise.inject.Instance;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import de.crowdcode.vehicle.cdi.calculator.CalculatorService;


public class CalculatorServiceTest {

    @Test
    public void test() {
        WeldContainer weld = new Weld().initialize();
        Instance<CalculatorService> instance = weld.instance().select(CalculatorService.class);
        CalculatorService service = instance.get();
        
        assertEquals(21, service.sum(Arrays.asList(1,2,3,4,5,6)));
        
        double resultDiv = service.div(42.0,2.0);
        assertEquals(21.0, resultDiv, 0.0001);
    }

}
