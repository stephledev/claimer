package ch.claimer.client.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.client.proxy.AuthenticationFilter;

public class ResteasyClientUtil {
	
	private static ResteasyWebTarget rtarget = null;
	 
	public static ResteasyWebTarget getTarget() {
		Config config = ConfigFactory.load();
		
		
	   if (rtarget == null) {
		   Client client = new ResteasyClientBuilder().build();
		   client.register(new AuthenticationFilter());
		   WebTarget target = client.target(config.getString("webservice.host") + "/" + config.getString("webservice.path"));
		   rtarget = (ResteasyWebTarget)target;
	   }	   
	   return rtarget;
	}
}
