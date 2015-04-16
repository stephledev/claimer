package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Category;

public interface CategoryMethod extends Remote {
	public List<Category> getAll();
}
