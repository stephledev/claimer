package ch.claimer.webservice.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.pmw.tinylog.Logger;

import ch.claimer.shared.util.LoggerUtil;

@WebListener
public class EventListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		LoggerUtil.loadConfig();
		
		Logger.info("Webservice gestartet");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
