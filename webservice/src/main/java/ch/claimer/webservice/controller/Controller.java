package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

public interface Controller<Id> {
	
	public Response index();
	public Response show(Id id);
	public Response store(String data);
	public Response update(String data);
	public Response destroy(String data);
	
}
