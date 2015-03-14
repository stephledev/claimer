package ch.claimer.webservice.repositories;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, Id extends Serializable> {
	
    T create(T t);
    T getById(Id id);
    List<T> getAll();
    T update(T t);
    void delete(T t);
    
}