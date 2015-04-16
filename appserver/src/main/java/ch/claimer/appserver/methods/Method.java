package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

public interface Method<T> extends Remote {
	public List<T> getAll();
	public T getById(Integer id);
	public T create(T t);
	public T update(T t);
	public Integer delete(Integer id);
}
