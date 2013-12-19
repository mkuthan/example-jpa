package ddd.infrastructure;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.orm.ObjectRetrievalFailureException;

import ddd.domain.Repository;

public abstract class AbstractJpaRepository<E, K extends Serializable> implements Repository<E, K> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractJpaRepository() {
		this.entityClass = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public E load(K id) {
		requireNonNull(id);

		E entity = entityManager.find(entityClass, id, LockModeType.OPTIMISTIC);

		if (entity == null) {
			throw new ObjectRetrievalFailureException(entityClass, id);

		}

		beanFactory.autowireBean(entity);

		return entity;
	}

	@Override
	public void save(E entity) {
		requireNonNull(entity);

		if (entityManager.contains(entity)) {
			entityManager.lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		} else {
			entityManager.persist(entity);
		}

		entityManager.flush();
	}

	@Override
	public void delete(E entity) {
		requireNonNull(entity);

		entityManager.remove(entity);
		entityManager.flush();
	}

}
