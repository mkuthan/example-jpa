package design.infrastructure.jpa.repositories;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import design.infrastructure.jpa.JpaConfig;

@ContextConfiguration(classes = JpaConfig.class)
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	protected EntityManager entityManager;

	protected void persistFlushAndClear(Object entity) {
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.clear();
	}

	protected boolean isExist(Class<?> entityClass, Serializable id) {
		return entityManager.find(entityClass, id) != null;
	}

	protected void clear() {
		entityManager.clear();
	}

}
