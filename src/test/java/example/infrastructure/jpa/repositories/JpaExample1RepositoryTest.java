package example.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example1.Example1;
import example.domain.example1.Example1Repository;

@JpaRepositoryTest
@Test
public class JpaExample1RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example1Repository repository;

	@Autowired
	private JpaRepositoryHelper helper;
	
	public void shouldSave() {
		// given
		String id = "any id";

		Example1 givenEntity = new Example1(id);

		// when
		repository.save(givenEntity);
		helper.clear();

		// then
		Example1 entity = repository.load(id);

		assertThat(entity.getId()).isEqualTo(id);
	}

	public void shouldDelete() {
		// given
		String id = "any id";
		helper.persistFlushAndClear(new Example1(id));
		Example1 entity = repository.load(id);

		// when
		repository.delete(entity);
		helper.clear();

		// then
		assertThat(helper.isExist(Example1.class, id)).isFalse();
	}
}
