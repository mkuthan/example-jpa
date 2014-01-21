package example.infrastructure.jpa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.domain.example8.Example8;
import example.domain.example8.Example8Repository;
import example.infrastructure.jpa.JpaTests;

@JpaTests
@Test
public class JpaExample8RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example8Repository repository;

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveDuplicates() {
		// given
		String name = "name";

		Example8 givenEntity1 = new Example8("id1", name);
		Example8 givenEntity2 = new Example8("id2", name);

		// when
		repository.save(givenEntity1);
		repository.save(givenEntity2);
	}
}
