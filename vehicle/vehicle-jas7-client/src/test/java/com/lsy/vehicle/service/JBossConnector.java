package com.lsy.vehicle.service;

import org.jboss.ejb.client.*;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.Context;
import javax.naming.NamingException;
import java.io.InputStream;
import java.util.Properties;

public class JBossConnector {

	// private String target;
	private static final String APP_NAME = "vehicle-ear";
	private static final String MODULE_NAME = "vehicle-ejb";
//	private static final String AUTH_MODULE_NAME = "tango-auth-ejb";
//	private static final String AUTH_APP_NAME = "tango-auth";


	private Context context;

	final Properties ejbClientProperties = new Properties();

	public JBossConnector() throws ConnectionException{
		try {
			createJBoss7Context();

		} catch (ConnectionException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConnectionException("Could not create JBoss context: " + ex.getMessage(), null);
		}
	}

	public void createJBoss7Context() throws ConnectionException {
		try {
			// setup EJB client context with both the servers
			try {
				final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jboss-ejb-client.properties");
				ejbClientProperties.load(inputStream);
				inputStream.close();
			} catch (Exception e) {
				throw new ConnectionException("Could not load EJB client configuration properties from jboss-ejb-client.properties", null);
			}
			
			final EJBClientConfiguration clientConfiguration = new PropertiesBasedEJBClientConfiguration(ejbClientProperties);
			ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(clientConfiguration);
			EJBClientContext.setSelector(selector);

		} catch (RuntimeException ex) {
			throw new ConnectionException("Error creating JBoss7 client context. ", ex);
		}
	}

	public Properties getEjbClientProperties() {
		return ejbClientProperties;
	}

	public ApplicationLogService getApplicationLogService() throws ConnectionException {
		try {
			final StatelessEJBLocator<ApplicationLogService> statelessEJBLocator = new StatelessEJBLocator<ApplicationLogService>(
					ApplicationLogService.class, APP_NAME, MODULE_NAME, "ApplicationLogServiceBean", "");

			return EJBClient.createProxy(statelessEJBLocator);
		} catch (Exception ex) {
			throw new ConnectionException("Error connecting to Generic Service.", ex);
		}
	}

	public void closeActiveListContext() throws NamingException {
		if (context != null) {
			context.close();
			context = null;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		closeActiveListContext();
		super.finalize();
	}

}
