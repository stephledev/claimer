package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.SCEmployee;

public class SCEmployeeController<T> extends DefaultController<SCEmployee> {

	public SCEmployeeController() {
		super(SCEmployee.class);
		// TODO Auto-generated constructor stub
	}

	public Response showBySubcontractor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
