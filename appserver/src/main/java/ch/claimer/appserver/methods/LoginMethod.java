package ch.claimer.appserver.methods;

import java.rmi.Remote;

import ch.claimer.shared.models.Login;

public interface LoginMethod extends Remote {
	public Login getByUsername();
	public Login create(Login login);
	public Login update(Login login);
	public Login delete(Integer id);
}
