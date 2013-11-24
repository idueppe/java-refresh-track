package de.crowdcode.training;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
@Stateless
@Local(Calculator.class)
@Remote(CalculatorRemote.class)
public class CalculatorBean implements Calculator
{

	private static final Log LOG = LogFactory.getLog(CalculatorBean.class);

	@Override
	public Double multiply(Double... factors)
	{
		if (factors.length < 2)
		{
			throw new IllegalArgumentException("This Method needs at least to parameters");
		}

		LOG.info("Start calculating... ");
		Double result = 1.0;
		for (Double factor : factors)
		{
			LOG.info("* " + factor);
			result *= factor;
		}
		LOG.info("...finished");
		return result;
	}

}
