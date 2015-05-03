package ch.claimer.appserver.repositories;

import java.util.List;

public interface Repository<T> {
	
    T getById(int id);
    List<T> getAll();
    List<T> getByProperty(String name, Object value);
    List<T> getByRelation(Class<?> relation, int id);
    T create(T t);
    T update(T t);
    void delete(int id);
    
}
