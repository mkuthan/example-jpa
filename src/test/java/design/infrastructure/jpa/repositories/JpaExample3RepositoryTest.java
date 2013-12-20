package design.infrastructure.jpa.repositories;

import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import design.domain.example3.Example3;
import design.domain.example3.Example3Detail;

@Test
public class JpaExample3RepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private JpaExample3Repository repository;

	public void shouldSave() {
		// given
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		Example3 givenEntity = new Example3("any id", details);

		// when
		repository.save(givenEntity);
		clear();

		// then
		Example3 entity = repository.load(givenEntity.getId());

		assertThat(entity.getDetails()).isEqualTo(details);
	}

	public void shouldDelete() {
		// given
		String id = "any id";
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		persistFlushAndClear(new Example3(id, details));
		Example3 entity = repository.load(id);

		// when
		repository.delete(entity);
		clear();

		// then
		assertThat(isExist(Example3.class, id)).isFalse();
	}

	@Test(enabled = false)
	public void shouldUpdate() {
		// given
		String id = "any id";
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		persistFlushAndClear(new Example3(id, details));
		Example3 entity = repository.load(id);

		// when
		Set<Example3Detail> newDetails = newHashSet(new Example3Detail("first"), new Example3Detail("second"));
		entity.setDetails(details);
		repository.save(entity);
		clear();

		// then
		entity = repository.load(id);
		assertThat(entity.getDetails()).isEqualTo(newDetails);
	}
}
