package ch.claimer.appserver.controller;

import java.rmi.RemoteException;

import ch.claimer.appserver.methods.TypeMethod;
import ch.claimer.shared.models.Type;

public class TypeController extends Controller<Type> implements TypeMethod {
	
	private static final long serialVersionUID = -8215198520379250469L;

	public TypeController() throws RemoteException {
		super(Type.class);
	}
}
