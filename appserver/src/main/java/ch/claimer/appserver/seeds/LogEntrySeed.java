package ch.claimer.appserver.seeds;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.LogEntry;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class LogEntrySeed extends Seed<LogEntry> {
	
	public LogEntrySeed() {
		this.repository = new EclipseLinkRepository<LogEntry>(LogEntry.class);
		this.seed = new ArrayList<LogEntry>();
	}

	@Override
	public void setup() {
		
		LogEntry log1 = new LogEntry();
		log1.setDate(new GregorianCalendar());
		log1.setDescription("Bauleiter wurde geänderet");	
		
		LogEntry log2 = new LogEntry();
		log2.setDate(new GregorianCalendar());
		log2.setDescription("Projekt wurde angepasst.");
		
		LogEntry log3 = new LogEntry();
		log3.setDate(new GregorianCalendar());
		log3.setDescription("Mangel wurde erfasst");
		
		LogEntry log4 = new LogEntry();
		log4.setDate(new GregorianCalendar());
		log4.setDescription("Mangel wurde geändert.");
		
		seed.add(log1);
		seed.add(log2);
		seed.add(log3);
		seed.add(log4);
	
		
		Seed.seeds.put("LogEntry", seed);
	}

}

