package ch.claimer.webservice.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Login;

public class AuthenticationService {

	private Login login;
	protected Method<Login> method;

	@SuppressWarnings("unchecked")
	public AuthenticationService() {
		Config config = ConfigFactory.load();
		try {
			this.method = (Method<Login>) Naming.lookup(config
					.getString("rmi.url") + "Login");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}

	public boolean authenticate(String basic) {
		basic = basic.replaceFirst("Basic ", "");
		basic = new String(Base64.getDecoder().decode(basic));
		String[] usernamePassword = basic.split(":");
		String username = usernamePassword[0]; 
		String password = usernamePassword[1];
		try { 
			login = method.getByProperty("username", username).get(0);
			if(login.getPassword() == password) {
				return true;
			} else {
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean authorize(List<String> roles) {
		return roles.containsAll(login.getRoles());
	}

}
