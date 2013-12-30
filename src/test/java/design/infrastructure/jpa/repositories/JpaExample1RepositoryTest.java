package design.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import design.domain.example1.Example1;

@Test
public class JpaExample1RepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private JpaExample1Repository repository;

	public void shouldSave() {
		// given
		Example1 givenEntity = new Example1("any id");

		// when
		repository.save(givenEntity);
		clear();

		// then
		Example1 entity = repository.load(givenEntity.getId());

		assertThat(entity.getId()).isEqualTo(givenEntity.getId());
	}

	public void shouldDelete() {
		// given
		String id = "any id";
		persistFlushAndClear(new Example1(id));
		Example1 entity = repository.load(id);

		// when
		repository.delete(entity);
		clear();

		// then
		assertThat(isExist(Example1.class, id)).isFalse();
	}
}
