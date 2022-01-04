package repos;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract class similar to the design pattern Data Mapper.
 * Encapsulates the common functionalities acting like a Layer Supertype.
 */
public abstract class DataMapper<T, ID extends Serializable> implements Serializable {

    protected Class<T> entityClass;

    @PersistenceContext(unitName = "searchable_encryption")
    protected EntityManager entityManager;

    protected DataMapper(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    protected void init() {
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    public T findById(ID id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(entityClass, id);
    }

    public List findAll() {
        String qlString = "select e from " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(qlString).getResultList();
    }

    public void clearCache() {
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }

    public DataMapper() {
    }
}
