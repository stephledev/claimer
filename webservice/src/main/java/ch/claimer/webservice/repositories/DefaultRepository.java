package ch.claimer.webservice.repositories;

import java.util.List;

public interface DefaultRepository<T> {

	T store(T t);
    T getById(Integer id);
    List<T> getAll();
    T update(T t);
    void destroy(Integer id);
    
}
