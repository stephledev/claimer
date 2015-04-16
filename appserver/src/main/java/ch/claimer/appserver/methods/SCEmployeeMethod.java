package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.SCEmployee;

public interface SCEmployeeMethod extends Remote {
	public List<SCEmployee> getByAll();
	public List<SCEmployee> getById();
	public SCEmployee create(SCEmployee scEmployee);
	public SCEmployee update(SCEmployee scEmployee);
}
