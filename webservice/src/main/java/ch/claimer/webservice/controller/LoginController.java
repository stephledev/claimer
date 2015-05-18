package ch.claimer.webservice.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.pmw.tinylog.Logger;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Person;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Behandelt Anfragen spezifisch für Logins. Behandelt die Prüfung des Anmeldeverfahren über die
 * RMI-Schnittstelle. 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
public class LoginController extends Controller<Login> {
	
	private Method<Person> personMethod;
	
	@SuppressWarnings("unchecked")
	public LoginController() {
		super(Login.class);
		Config config = ConfigFactory.load();
		try {
			this.personMethod = (Method<Person>) Naming.lookup(config
					.getString("rmi.host")
					+ "/person");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
	}

	/**
	 * Überprüft ob das eingehende Login in der Datenbank vorhanden ist.
	 * 
	 * @param login zu prüfendes Login
	 * @return Response HTTP-Antwort mit zugehöriger Person zum Login
	 */
	public Response check(Login login) {
		List<Login> checkLogin = null;
		try {
			checkLogin = (List<Login>) method.getByProperty("username", login.getUsername());
			if(!checkLogin.isEmpty()) {
				login = checkLogin.get(0);
				if(login.getPassword().equals(login.getPassword())) {
					Person person = personMethod.getByRelation(Login.class, login.getId()).get(0);
					return ResponseHandlerService.success(person);
				}
			}
		} catch (IOException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(new Login());
	}
}
