package ch.claimer.appserver.repositories;

import java.util.List;

public interface Repository<T> {
	
    T getById(Integer id);
    List<T> getAll();
    List<T> getByProperty(String name, List<?> values);
    T create(T t);
    T update(T t);
    void delete(Integer id);
    
}
