package ch.claimer.webservice.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.pmw.tinylog.Logger;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.webservice.services.ResponseHandlerService;

public class GCEmployeeController extends Controller<GCEmployee> {
	
	public GCEmployeeController() {
		super(GCEmployee.class);
	}

	@Override
	public Response showByProperty(String name, Object value) {
		GenericEntity<List<GCEmployee>> genericModels = null;
		try {
			List<GCEmployee> models = method.getByProperty(name, value);
			genericModels = new GenericEntity<List<GCEmployee>>(models){};
			return ResponseHandlerService.success(genericModels);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(genericModels);
		
	}
}
