package ch.claimer.webservice.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.pmw.tinylog.Logger;

import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.services.ResponseHandlerService;

public class SupervisorController extends Controller<Supervisor> {
	
	public SupervisorController() {
		super(Supervisor.class);
	}

	@Override
	public Response showByProperty(String name, Object value) {
		GenericEntity<List<Supervisor>> genericModels = null;
		try {
			List<Supervisor> models = method.getByProperty(name, value);
			genericModels = new GenericEntity<List<Supervisor>>(models){};
			return ResponseHandlerService.success(genericModels);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(genericModels);
		
	}
}
