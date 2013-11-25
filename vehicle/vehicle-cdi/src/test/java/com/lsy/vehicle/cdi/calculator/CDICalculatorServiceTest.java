package com.lsy.vehicle.cdi.calculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(CdiRunner.class)
@AdditionalClasses({ CalculatorServiceBean.class, CalculatorDecorator.class })
public class CDICalculatorServiceTest
{

	@Inject
	private CalculatorService	service;

	@Mock
	@Produces
	@FastCalculator
	private CalculatorBean		calculator;

	@Test
	public void testSum()
	{
		when(calculator.sum((Integer[]) anyVararg())).thenReturn(10);
		assertEquals(10, service.sum(Arrays.asList(1, 2, 3, 4)));
		// verify(calculator,times(1)).sum((Integer[])anyVararg());
		verify(calculator, times(1)).sum();
	}

	@Test
	public void testDiv()
	{
		assertEquals(10.0, service.div(20.0, 2.0), 0.0001);
	}

}
