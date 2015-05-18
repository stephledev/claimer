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
import ch.claimer.shared.models.Model;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Behandelt Anfragen, die �ber die HTTP-Routes vermittelt wurden.
 * Initiert eine Verbindung zur RMI-Schnittstelle und ruft die entsprechenden
 * Methoden.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
public class Controller<T extends Model> {

	protected final Class<T> clazz;
	protected Method<T> method;

	@SuppressWarnings("unchecked")
	public Controller(Class<T> clazz) {
		this.clazz = clazz;

		Config config = ConfigFactory.load();
		try {
			this.method = (Method<T>) Naming.lookup(config
					.getString("rmi.host")
					+ "/"
					+ clazz.getSimpleName().toLowerCase());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
	}

	/**
	 * Zeigt alle Entit�ten an via RMI-Aufruf an.
	 * 
	 * @return Response HTTP-Antwort mit Entit�ten
	 */
	public Response showAll() {
		List<T> models = null;
		try {
			models = method.getAll();
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(models);
	}

	/**
	 * Zeigt eine bestimmte Entit�t via RMI-Aufruf an.
	 * 
	 * @param id Identifikator der Entit�t
	 * @return Response HTTP-Antwort mit Entit�t
	 */
	public Response showById(int id) {
		T model = null;
		try {
			model = method.getById(id);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(model);
	}

	/**
	 * Zeigt alle Entit�ten mit einer bestimmten Eigenschaft via RMI-Aufruf an.
	 * 
	 * @param name Name der Eigeschaft
	 * @param value Wert der Eigenschaft
	 * @return Response HTTP-Antwort mit Entit�ten
	 */
	public Response showByProperty(String name, Object value) {
		List<T> models = null;
		try {
			models = method.getByProperty(name, value);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(models);
	}

	/**
	 * Zeigt alle Entit�ten einer Beziehung via RMI-Aufruf an.
	 * 
	 * @param relation Klassen-Typ der Beziehung
	 * @param id Identifikator der Beziehung der anzuzeigenden Entit�ten
	 * @return Response HTTP-Antwort mit Entit�ten
	 */
	public Response showByRelation(Class<?> relation, int id) {
		List<T> models = null;
		try {
			models = method.getByRelation(relation, id);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(models);
	}
	
	/**
	 * Zeigt alle Entit�ten einer Mehrfach-Beziehung via RMI-Aufruf an.
	 * 
	 * @param relation Klassen-Typ der Beziehung
	 * @param id Identifikator der Mehrfach-Beziehung der anzuzeigenden Entit�ten
	 * @return Response HTTP-Antwort mit Entit�ten
	 */
	public Response showByRelations(Class<?> relation, int id) {
		List<T> models = null;
		try {
			models = method.getByRelations(relation, id);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		return ResponseHandlerService.success(models);
	}

	/**
	 * Aktualisert eine bestehende Entit�t via RMI-Aufruf.
	 * 
	 * @param model zu aktualisierende Entit�t
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	public Response update(T model) {
		try {
			model = method.update(model);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		Logger.info(clazz.getSimpleName() + " mit Id " + model.getId()
				+ " aktualisiert");
		return ResponseHandlerService.success();
	}

	/**
	 * Legt eine neue Entit�t via RMI-Aufruf an.
	 * 
	 * @param model neu anzulegende Entit�t
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	public Response store(T model) {
		try {
			model = method.create(model);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		Logger.info(clazz.getSimpleName() + " mit Id " + model.getId()
				+ " hinzugef�gt");
		return ResponseHandlerService.success();
	}

	/**
	 * L�scht eine bestehende Entit�t via RMI-Aufruf.
	 * 
	 * @param id Identifikator der zu l�schenden Entit�t
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	public Response destroy(int id) {
		try {
			method.delete(id);
		} catch (RemoteException e) {
			Logger.error(e, "Verbindung mit RMI-Dienst fehlgeschlagen");
		}
		Logger.info(clazz.getSimpleName() + " mit Id " + id + " gel�scht");
		return ResponseHandlerService.success(String.valueOf(id));
	}
}
