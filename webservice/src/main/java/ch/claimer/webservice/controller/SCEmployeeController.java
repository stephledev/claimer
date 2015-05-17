package ch.claimer.webservice.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.pmw.tinylog.Logger;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.services.ResponseHandlerService;

public class SCEmployeeController extends Controller<SCEmployee> {
	
	public SCEmployeeController() {
		super(SCEmployee.class);
	}

	@Override
	public Response showByProperty(String name, Object value) {
		GenericEntity<List<SCEmployee>> genericModels = null;
		try {
			List<SCEmployee> models = method.getByProperty(name, value);
			genericModels = new GenericEntity<List<SCEmployee>>(models){};
			return ResponseHandlerService.success(genericModels);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(genericModels);
	}
	
	@Override
	public Response showByRelation(Class<?> relation, int id) {
		GenericEntity<List<SCEmployee>> genericModels = null;
		try {
			List<SCEmployee> models = method.getByRelation(relation, id);
			genericModels = new GenericEntity<List<SCEmployee>>(models){};
			models = method.getByRelation(relation, id);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(genericModels);
	}
}
