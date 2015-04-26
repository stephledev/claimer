package ch.claimer.webservice.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Base64;

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

	public String authenticate(String username, String password) {
		String auth = request.getHeader("Authorization").replaceFirst("Basic ", "");
		auth = new String(Base64.getDecoder().decode(auth));
		System.out.println(auth);
		try {
			Login login = method.getAll().get(0);
			return login.getUsername();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void authorize(String[] roles) {
		login.getRoles();
	}

}
