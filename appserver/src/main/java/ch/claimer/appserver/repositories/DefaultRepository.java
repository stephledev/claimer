package ch.claimer.appserver.repositories;

import java.util.List;

public interface DefaultRepository<T> {

	T create(T t);
    T getById(Integer id);
    List<T> getAll();
    T update(T t);
    void delete(Integer id);
    
}
