package ch.claimer.appserver.repositories;

import java.util.List;

public interface Repository<T> {
	
    T getById(int id);
    List<T> getAll();
    List<T> getByProperty(String name, List<?> values);
    T create(T t);
    T update(T t);
    void delete(int id);
    
}
