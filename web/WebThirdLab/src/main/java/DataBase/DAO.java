package DataBase;

import java.util.List;

public interface DAO<E, K> {
    List<E> getAll();

    E getEntityById(K id);

    void delete(K id);

    void create(E entity);
}
