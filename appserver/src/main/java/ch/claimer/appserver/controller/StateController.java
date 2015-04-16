package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.StateMethod;
import ch.claimer.shared.models.State;

public class StateController extends Controller<State> implements StateMethod {
	
	private static final long serialVersionUID = -8215198520379250469L;

	public StateController() throws RemoteException {
		super(State.class);
	}
}
