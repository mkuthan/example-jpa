package example.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import example.domain.example1.Example1;
import example.domain.example1.Example1Repository;

@Test
public class JpaExample1RepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private Example1Repository repository;

	public void shouldSave() {
		// given
		String id = "any id";
		
		Example1 givenEntity = new Example1(id);

		// when
		repository.save(givenEntity);
		clear();

		// then
		Example1 entity = repository.load(id);

		assertThat(entity.getId()).isEqualTo(id);
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
