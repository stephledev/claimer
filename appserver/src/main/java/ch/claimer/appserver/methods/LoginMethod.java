package ch.claimer.appserver.methods;

import ch.claimer.shared.models.Login;

public interface LoginMethod extends Method<Login> {
	public Login getByUsername();
}
