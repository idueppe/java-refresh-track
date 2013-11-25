package de.crowdcode.cdi.performance;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;


@Monitored
@Interceptor
public class PerformanceMonitor {

	private static final Logger log = Logger.getLogger(PerformanceMonitor.class);
	
	@AroundInvoke
	public Object printExecutionTime(InvocationContext context) throws Exception
	{
		long startms = System.currentTimeMillis();
		
		Object result = context.proceed();
		
		long time = System.currentTimeMillis() - startms;
		
		log.info("Execution of "+context.getMethod().getName()+" took "+time+" ms.");
		
		return result;
	}
	
}
