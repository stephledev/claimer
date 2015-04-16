package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.GCEmployeeMethod;
import ch.claimer.shared.models.GCEmployee;

public class GCEmployeeController extends Controller<GCEmployee> implements GCEmployeeMethod {

	private static final long serialVersionUID = -5724376567173685470L;
	
	public GCEmployeeController() throws RemoteException {
		super(GCEmployee.class);
	}
}
