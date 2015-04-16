package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Method<T> extends Remote {
	public List<T> getAll() throws RemoteException;
	public T getById(Integer id) throws RemoteException;
	public T create(T t) throws RemoteException;
	public T update(T t) throws RemoteException;
	public Integer delete(Integer id) throws RemoteException;
}
