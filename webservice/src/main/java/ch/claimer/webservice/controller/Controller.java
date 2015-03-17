package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

public interface Controller<Id> {
	
	public void index();
	public Response show(Id id);
	public void create();
	public void store();
	public void edit(Id id);
	public void update(Id id);
	public void destroy(Id id);
	
	

}
