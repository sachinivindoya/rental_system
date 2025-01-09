package dao;

import java.util.List;

public interface CrudDAO<T, ID> {
    boolean add(T entity);
    boolean update(T entity);
    boolean delete(ID id);
    T find(ID id);
    List<T> findAll();
}
