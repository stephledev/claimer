package ch.claimer.webservice.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.Response;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Model;
import ch.claimer.webservice.services.ResponseHandlerService;


/**
 * Behandelt sämtliche Anfragen, die über die HTTP-Routes vermittelt wurden.
 * Initiert eine Verbindung zur RMI-Schnittstelle und ruft die entsprechenden Methoden.
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
			this.method = (Method<T>) Naming.lookup(config.getString("rmi.url") + clazz.getSimpleName());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public Response showAll() {
		List<T> models = null;
		try {
			models = method.getAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(models);
	}
	
	public Response showById(int id) {
		T model = null;
		try {
			model = method.getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(model);
	}
	
	public Response showByProperty(String name, Object value) {
		List<T> models = null;
		try {
			models = method.getByProperty(name, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(models);
	}
	
	public Response showByRelation(Class<?> relation, int id) {
		List<T> models = null;
		try {
			models = method.getByRelation(relation, id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(models);
	}
	
	public Response update(T model) {
		try {
			model = method.update(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(clazz.getSimpleName() + " with Id " + model.getId() + " updated");
	}
	
	public Response store(T model) {
		try {
			model = method.create(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(clazz.getSimpleName() + " added with Id " + model.getId());
	}
	
	public Response destroy(int id) {
		try {
			method.delete(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(String.valueOf(id));
	}
}
