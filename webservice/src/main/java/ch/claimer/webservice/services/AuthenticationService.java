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
import ch.claimer.shared.models.Role;

/**
 * Hilft eingehende HTTP-Anfragen zu authentifizieren. Prüft via
 * RMI-Schnittstelle ob ein entsprechendes Login in der Datenbank vorhanden ist.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 */
public class AuthenticationService {

	private Login login;
	private Method<Login> loginMethod;
	private Method<Role> roleMethod;

	@SuppressWarnings("unchecked")
	public AuthenticationService() {
		Config config = ConfigFactory.load();
		try {
			this.loginMethod = (Method<Login>) Naming.lookup(config
					.getString("rmi.host") + "/login");
			this.roleMethod = (Method<Role>) Naming.lookup(config
					.getString("rmi.host") + "/role");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Authentifiziert die HTTP-Anfrage mittels übergebenen HTTP-Header.
	 * 
	 * @param basic HTTP-Header Authenifizierungs-Informationen
	 * @return boolean true bei Erfolg, false falls Login nicht existiert
	 */
	public boolean authenticate(String basic) {
		basic = basic.replaceFirst("Basic ", "");
		basic = new String(Base64.getDecoder().decode(basic));
		String[] usernamePassword = basic.split(":");
		String username = usernamePassword[0];
		String password = usernamePassword[1];
		try {
			if (loginMethod.getByProperty("username", username).isEmpty()) {
				return false;
			}
			login = loginMethod.getByProperty("username", username).get(0);
			if (login.getPassword().equals(password)) {
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Prüft den Benutzer der HTTP-Anfrage auf die nötigen Rollen
	 * 
	 * @param roles benötige Rollen
	 * @return boolean true bei Erfolg, false falls benötige Rollen nicht  vorhanden
	 */
	public boolean authorize(List<String> roles) {
		Role requiredRole = null;
		try {
			requiredRole = roleMethod.getByProperty("name", roles.get(0))
					.get(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (login.getRole().getValue() >= requiredRole.getValue()) {
			return true;
		}
		return false;
	}

}
