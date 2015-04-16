package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.GCEmployee;

public interface GCEmployeeMethod extends Remote {
	public List<GCEmployee> getByAll();
	public List<GCEmployee> getById();
	public GCEmployee create(GCEmployee gcEmployee);
	public GCEmployee update(GCEmployee gcEmployee);
}
