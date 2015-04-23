package ch.claimer.webservice.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Model;
import ch.claimer.webservice.services.ConverterService;
import ch.claimer.webservice.services.JsonConverterService;

public class Controller<T extends Model> {

	protected final Class<T> clazz;
	//protected final AuthenticationService authentication;
	protected final ConverterService<T> converter;
	//protected final ResponseHandlerService responseHandler;
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
		return Response.status(Status.OK).entity(converter.write(models)).build();
	}
	
	public Response showById(int id) {
		T model = null;
		try {
			model = method.getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(converter.write(model)).build();
	}
	
	public Response showByProperty(String name, Object value) {
		T model = null;
		try {
			model = method.getByProperty(name, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(converter.write(model)).build();
	}
	
	public Response update(String modelString) {
		T model = converter.read(modelString, clazz);
		try {
			model = method.update(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(modelString).build();
	}
	
	public Response store(String modelString) {
		T model = converter.read(modelString, clazz);
		try {
			model = method.create(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(modelString).build();
	}
	
	public Response destroy(int id) {
		try {
			method.delete(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(id).build();
	}
}
