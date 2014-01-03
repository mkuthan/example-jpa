package design.infrastructure.jpa.repositories;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import design.domain.example5.audit.Auditor;
import design.domain.example6.clob.ClobSerializer;
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

	@Configuration
	public static class Config {

		@Bean
		public Auditor auditor() {
			return Mockito.mock(Auditor.class);
		}

		@Bean
		public ClobSerializer clobSerializer() {
			return Mockito.mock(ClobSerializer.class);
		}

	}

}
