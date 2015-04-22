package ch.claimer.shared.methods;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Method<T> extends Remote {
	public List<T> getAll() throws RemoteException;
	public T getById(int id) throws RemoteException;
	public List<T> getByProperty(String name, List<?> values) throws RemoteException;
	public T create(T t) throws RemoteException;
	public T update(T t) throws RemoteException;
	public Integer delete(int id) throws RemoteException;
}
