package example.infrastructure.jpa.repositories;

import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example4.Example4;
import example.domain.example4.Example4Repository;
import example.domain.example4.Example4Service;

@JpaRepositoryTest
@Test
public class JpaExample4RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example4Repository repository;

	@Autowired
	private Example4Service service;
	
	@Autowired
	private JpaRepositoryHelper helper;

	public void shouldCallAutowiredService() {
		// given
		String id = "any id";
		helper.persistFlushAndClear(new Example4("any id"));

		// when
		Example4 entity = repository.load(id);
		entity.callService();

		// then
		verify(service).call();
	}

	@Configuration
	public static class Config {

		@Bean
		public Example4Service example4Service() {
			return Mockito.mock(Example4Service.class);
		}

	}
}
