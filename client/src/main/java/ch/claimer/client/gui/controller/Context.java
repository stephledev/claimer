package ch.claimer.client.gui.controller;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;

public class Context {
	
	private Person person = new GCEmployee();
	
	public Person getPerson() {
		return person;
	}
	
}
