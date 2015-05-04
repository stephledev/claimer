package ch.claimer.client.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ResteasyClientUtil {
	
	private static ResteasyWebTarget rtarget = null;
	 
   public static synchronized ResteasyWebTarget getTarget() {
	   if (rtarget == null) {
		   Client client = new ResteasyClientBuilder().build();
		   WebTarget target = client.target("http://localhost:8080/webservice");
		   rtarget = (ResteasyWebTarget)target;
	   }	   
	   return rtarget;
   }
}
