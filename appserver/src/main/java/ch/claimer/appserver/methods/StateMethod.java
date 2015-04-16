package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.State;

public interface StateMethod extends Remote {
	public List<State> getAll();
}
