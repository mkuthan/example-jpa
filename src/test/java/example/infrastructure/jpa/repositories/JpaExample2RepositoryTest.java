package example.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example2.Example2;
import example.domain.example2.Example2Identifier;
import example.domain.example2.Example2Repository;

@JpaRepositoryTest
@Test
public class JpaExample2RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example2Repository repository;

	@Autowired
	private JpaRepositoryHelper helper;
	
	public void shouldSave() {
		// given
		Example2Identifier id = new Example2Identifier("any id");
		
		Example2 givenEntity = new Example2(id);

		// when
		repository.save(givenEntity);
		helper.clear();

		// then
		Example2 entity = repository.load(id);

		assertThat(entity.getId()).isEqualTo(id);
	}

	public void shouldDelete() {
		// given
		Example2Identifier id = new Example2Identifier("any id");
		helper.persistFlushAndClear(new Example2(id));
		Example2 entity = repository.load(id);

		// when
		repository.delete(entity);
		helper.clear();

		// then
		assertThat(helper.isExist(Example2.class, id)).isFalse();
	}
}
