package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Type;

public interface TypeMethod extends Remote {
	public List<Type> getAll();
}
