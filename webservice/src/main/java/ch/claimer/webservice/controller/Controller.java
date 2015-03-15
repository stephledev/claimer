package ch.claimer.webservice.controller;

public interface Controller<Id> {
	
	public void index();
	public void show(Id id);
	public void create();
	public void store();
	public void edit(Id id);
	public void update(Id id);
	public void destroy(Id id);
	
	

}
