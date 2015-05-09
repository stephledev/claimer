package ch.claimer.appserver.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EclipseLinkUtil {

	private static EntityManagerFactory entityManagerFactory = null;

	public static synchronized EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			Config config = ConfigFactory.load();
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.driver",
					config.getString("javax.persistence.jdbc.driver"));
			properties.put("javax.persistence.jdbc.url",
					config.getString("javax.persistence.jdbc.url"));
			properties.put("javax.persistence.jdbc.user",
					config.getString("javax.persistence.jdbc.user"));
			properties.put("javax.persistence.jdbc.password",
					config.getString("javax.persistence.jdbc.password"));
			properties.put("eclipselink.ddl-generation.output-mode",
					config.getString("eclipselink.ddl-generation.output-mode"));
			properties.put("eclipselink.ddl-generation",
					config.getString("eclipselink.ddl-generation.value"));
			properties.put("eclipselink.logging.level",
					config.getString("eclipselink.logging.level"));

			entityManagerFactory = Persistence.createEntityManagerFactory(
					"claimer", properties);

		}
		return entityManagerFactory;
	}
}