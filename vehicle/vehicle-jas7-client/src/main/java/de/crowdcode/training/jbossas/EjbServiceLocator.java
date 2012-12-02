package de.crowdcode.training.jbossas;

import com.lsy.vehicle.service.ApplicationLogService;
import com.lsy.vehicle.service.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public class EjbServiceLocator {
	
	/**
	 * 	The JNDI lookup name for a stateless session bean has the syntax of:
	 *  ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
	 * 
	 * 
	 * @param appName The application name is the name of the EAR that the EJB is deployed in 
	 *          (without the .ear).  If the EJB JAR is not deployed in an EAR then this is
	 *          blank.  The app name can also be specified in the EAR's application.xml
	 * @param moduleName By the default the module name is the name of the EJB JAR file (without the
	 *             .jar suffix).  The module name might be overridden in the ejb-jar.xml
	 * @param distinctName AS7 allows each deployment to have an (optional) distinct name. 
	 * @param beanName The name of the session been to be invoked.
	 * @param viewClass The fully class of the remote interface. 
	 * @return the ejb stub.
	 * @throws NamingException if name cannot be resolved
	 * @throws ConnectionException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupService(String appName, String moduleName, String distinctName, String beanName, Class<T> viewClass) throws NamingException, ConnectionException {
		final Context context = new InitialContext(loadEjbClientProperties());
		return (T) context.lookup("vehicle-ear/vehicle-ejb/ApplicationLogServiceBean!com.lsy.vehicle.service.ApplicationLogService");
//		return (T) context.lookup("ejb:/"+appName+"/"+moduleName+"/"+beanName+"!"+viewClass.getName());
	}
	
	private static Properties loadEjbClientProperties() throws ConnectionException {
		try {
			final InputStream inputStream = EjbServiceLocator.class.getClassLoader().getResourceAsStream("jboss-ejb-client.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			properties.put(Context.PROVIDER_URL,"remote://localhost:4447");
			properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
			inputStream.close();
			return properties;
		} catch (Exception e) {
			throw new ConnectionException("Could not load EJB client configuration properties from jboss-ejb-client.properties", null);
		}

	}
	
    public static ApplicationLogService lookupRemoteStatefulCounter() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        return (ApplicationLogService) context.lookup("java:/vehicle-ear/vehicle-ejb/ApplicationLogBean?stateful");//+ApplicationLogService.class.getName());
    }

}
