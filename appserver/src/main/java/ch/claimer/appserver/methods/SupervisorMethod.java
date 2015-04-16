package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Supervisor;

public interface SupervisorMethod extends Remote {
	public List<Supervisor> getByAll();
	public Supervisor getById(Integer id);
	public Supervisor create(Supervisor supervisor);
	public Supervisor update(Supervisor supervisor);
}
