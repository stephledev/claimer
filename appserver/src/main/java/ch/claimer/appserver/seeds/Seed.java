package ch.claimer.appserver.seeds;

import java.util.List;

import ch.claimer.appserver.repositories.Repository;
import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Model;

public abstract class Seed {
	
	protected List<? extends Model> list;
	protected Repository<? extends Model> repository;
	
	public abstract void setup();
	public void execute() {
		for(Model model : list) {
			repository.create(model);
		}
	}
}