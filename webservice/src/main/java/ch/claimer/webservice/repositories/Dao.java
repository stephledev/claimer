package ch.claimer.webservice.repositories;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, Id extends Serializable> {
	
    T store(T t);
    T getById(Id id);
    List<T> getAll();
    T update(T t);
    void destroy(T t);
    
}