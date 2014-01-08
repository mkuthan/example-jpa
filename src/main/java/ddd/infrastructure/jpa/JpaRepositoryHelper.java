package ddd.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class JpaRepositoryHelper {

	@PersistenceContext
	private EntityManager entityManager;

	public void persistFlushAndClear(Object entity) {
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.clear();
	}

	public boolean isExist(Class<?> entityClass, Object id) {
		return entityManager.find(entityClass, id) != null;
	}

	public void clear() {
		entityManager.clear();
	}
}
