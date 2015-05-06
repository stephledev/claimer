package ch.claimer.appserver.repositories;

import java.util.List;

import ch.claimer.shared.models.Model;

public interface Repository<T extends Model> {
	
    T getById(int id);
    List<T> getAll();
    List<T> getByProperty(String name, Object value);
    List<T> getByRelation(Class<?> relation, int id);
    T create(T t);
    T update(T t);
    void delete(int id);
    
}
