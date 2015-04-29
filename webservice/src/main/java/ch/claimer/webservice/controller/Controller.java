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
import ch.claimer.webservice.services.AuthenticationService;
import ch.claimer.webservice.services.ConverterService;
import ch.claimer.webservice.services.JsonConverterService;
import ch.claimer.webservice.services.ResponseHandlerService;

public class Controller<T extends Model> {

	protected final Class<T> clazz;
	protected AuthenticationService authentication;
	protected final ConverterService<T> converter;
	protected Method<T> method;
	
	
	@SuppressWarnings("unchecked")
	public Controller(Class<T> clazz) {
		this.clazz = clazz;
		this.converter = new JsonConverterService<T>();
		
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
		return ResponseHandlerService.success(converter.write(model));
	}
	
	public Response showByProperty(String name, Object value) {
		List<T> models = null;
		try {
			models = method.getByProperty(name, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(converter.write(models));
	}
	
	public Response update(String modelString) {
		T model = converter.read(modelString, clazz);
		try {
			model = method.update(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(modelString);
	}
	
	public Response store(String modelString) {
		T model = converter.read(modelString, clazz);
		try {
			model = method.create(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResponseHandlerService.success(modelString);
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
