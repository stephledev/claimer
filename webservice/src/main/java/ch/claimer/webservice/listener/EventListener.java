package ch.claimer.webservice.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EventListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
