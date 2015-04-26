package ch.claimer.appserver.seeds;

import java.util.List;

import ch.claimer.appserver.repositories.Repository;

public abstract class Seed<T> {
	
	protected List<T> list;
	protected Repository<T> repository;
	
	public static void main(String[] args) {
		CategorySeed categorySeed = new CategorySeed();
		categorySeed.setup();
		categorySeed.execute();
		
		StateSeed stateSeed = new StateSeed();
		stateSeed.setup();
		stateSeed.execute();
		
		TypeSeed typeSeed = new TypeSeed();
		typeSeed.setup();
		typeSeed.execute();
	}
	
	public abstract void setup();
	public void execute() {
		for(T t : list) {
			repository.create(t);
		}
	}
}