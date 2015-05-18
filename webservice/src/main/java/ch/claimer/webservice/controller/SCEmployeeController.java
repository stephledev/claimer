package ch.claimer.webservice.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.pmw.tinylog.Logger;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Behandelt Anfragen spezifisch für Sachbearbeiter Subunternehmen, die
 * über die HTTP-Routes vermittelt wurden. Initiert eine Verbindung zur
 * RMI-Schnittstelle und ruft die entsprechenden Methoden. Nötig da
 * GenericEntity nicht mit generischen Typen ausgestattet werden knann.
 * 
 * @see <a href="http://docs.oracle.com/javaee/6/api/javax/ws/rs/core/GenericEntity.html">GenericEntity</a>
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
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
