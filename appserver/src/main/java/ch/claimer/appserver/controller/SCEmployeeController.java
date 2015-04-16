package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.SCEmployeeMethod;
import ch.claimer.shared.models.SCEmployee;

public class SCEmployeeController extends Controller<SCEmployee> implements SCEmployeeMethod {
	
	private static final long serialVersionUID = -6409324912280672883L;
	
	public SCEmployeeController() throws RemoteException {
		super(SCEmployee.class);
	}

}
