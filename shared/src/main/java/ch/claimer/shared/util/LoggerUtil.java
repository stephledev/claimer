package ch.claimer.shared.util;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.labelers.TimestampLabeler;
import org.pmw.tinylog.policies.DailyPolicy;
import org.pmw.tinylog.writers.RollingFileWriter;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LoggerUtil {
	public static void loadConfig() {
		
		Config config = ConfigFactory.load();
		
		Configurator.defaultConfig()
		   .writer(new RollingFileWriter(config.getString("tinylog.filename"), 
				   config.getInt("tinylog.backups"), 
				   new TimestampLabeler("dd-MM-yyyy"), 
				   new DailyPolicy()))
		   .level(Level.valueOf(config.getString("tinylog.level")))
		   .activate();
	}
}
