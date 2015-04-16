package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.SubcontractorMethod;
import ch.claimer.shared.models.Subcontractor;

public class SubcontractorController extends Controller<Subcontractor> implements SubcontractorMethod {
	
	private static final long serialVersionUID = -6409324912280672883L;
	
	public SubcontractorController() throws RemoteException {
		super(Subcontractor.class);
	}

}
