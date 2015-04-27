package ch.claimer.appserver.seeds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.claimer.appserver.repositories.Repository;

public abstract class Seed<T> {
	
	protected Map<String, List<T>> seeds = new HashMap<String, List<T>>();
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
		for(List<T> seed : seeds.values()) {
			for(T t : seed) {
				repository.create(t);
			}
		}
	}
}