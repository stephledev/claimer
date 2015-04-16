package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.SupervisorMethod;
import ch.claimer.shared.models.Supervisor;

public class SupervisorController extends Controller<Supervisor> implements SupervisorMethod {
	
	private static final long serialVersionUID = -6409324912280672883L;
	
	public SupervisorController() throws RemoteException {
		super(Supervisor.class);
	}
	
}
