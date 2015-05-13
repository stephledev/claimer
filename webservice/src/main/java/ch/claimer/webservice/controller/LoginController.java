package ch.claimer.webservice.controller;

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
import ch.claimer.webservice.services.ResponseHandlerService;

public class LoginController {
	
	private Method<Login> method;
	
	@SuppressWarnings("unchecked")
	public LoginController() {
		Config config = ConfigFactory.load();
		try {
			this.method = (Method<Login>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/login");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
	}
	
	public Response check(Login login) {
		List<Login> checkLogin = null;
		try {
			checkLogin = (List<Login>) method.getByProperty("username", login.getUsername());
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		if(!checkLogin.isEmpty()) {
			if(checkLogin.get(0).getPassword().equals(login.getPassword())) {
				return ResponseHandlerService.success(checkLogin.get(0));
			} 
		}
		return ResponseHandlerService.success(new Login());
	}
}
