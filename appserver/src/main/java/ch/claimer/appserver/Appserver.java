package ch.claimer.appserver;

import org.pmw.tinylog.Logger;

import ch.claimer.appserver.util.RMIUtil;
import ch.claimer.shared.util.LoggerUtil;

/**
 * Startet den Appserver, indem sämtliche RMI-Dienste registriert werden.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
public class Appserver {

	public static void main(String[] args) {

		LoggerUtil.loadConfig();
		
		Logger.info("Appserver gestartet");

		RMIUtil.register();

	}

}
