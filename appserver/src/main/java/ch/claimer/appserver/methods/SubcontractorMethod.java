package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Subcontractor;

public interface SubcontractorMethod extends Remote {
	public List<Subcontractor> getByAll();
	public List<Subcontractor> getById();
	public Subcontractor create(Subcontractor subcontractor);
	public Subcontractor update(Subcontractor subcontractor);
}
