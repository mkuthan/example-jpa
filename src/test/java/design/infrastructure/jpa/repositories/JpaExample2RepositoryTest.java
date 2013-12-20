package design.infrastructure.jpa.repositories;

import static org.fest.assertions.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import design.domain.example2.Example2;
import design.domain.example2.Example2Identifier;

@Test
public class JpaExample2RepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private JpaExample2Repository repository;

	public void shouldSave() {
		// given
		Example2 givenEntity = new Example2(new Example2Identifier("any id"));

		// when
		repository.save(givenEntity);
		clear();

		// then
		Example2 entity = repository.load(givenEntity.getId());

		assertThat(entity.getId()).isEqualTo(givenEntity.getId());
	}

	public void shouldDelete() {
		// given
		Example2Identifier id = new Example2Identifier("any id");
		persistFlushAndClear(new Example2(id));
		Example2 entity = repository.load(id);

		// when
		repository.delete(entity);
		clear();

		// then
		assertThat(isExist(Example2.class, id)).isFalse();
	}
}
