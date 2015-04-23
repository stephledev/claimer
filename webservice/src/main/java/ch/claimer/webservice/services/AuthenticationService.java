package ch.claimer.webservice.services;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Login;

public class AuthenticationService {
	
	private Login login;
	protected Method<Login> method;
	
	public void authenticate(String username, String password)  {
		try {
			List<Login> list = method.getByProperty("username", username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void authorize(String[] roles) {
		login.getRoles();
	}
	
	
}
