package example.infrastructure.jpa.repositories;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example3.Example3;
import example.domain.example3.Example3Detail;
import example.domain.example3.Example3Repository;

@JpaRepositoryTest
@Test
public class JpaExample3RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example3Repository repository;

	@Autowired
	private JpaRepositoryHelper helper;
	
	public void shouldSave() {
		// given
		String id = "any id";
		
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		Example3 givenEntity = new Example3(id, details);

		// when
		repository.save(givenEntity);
		helper.clear();

		// then
		Example3 entity = repository.load(id);

		assertThat(entity.getDetails()).isEqualTo(details);
	}

	public void shouldDelete() {
		// given
		String id = "any id";
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		helper.persistFlushAndClear(new Example3(id, details));
		Example3 entity = repository.load(id);

		// when
		repository.delete(entity);
		helper.clear();

		// then
		assertThat(helper.isExist(Example3.class, id)).isFalse();
	}

	@Test
	public void shouldEditDetails() {
		// given
		String id = "any id";
		Set<Example3Detail> details = newHashSet(new Example3Detail("first"), new Example3Detail("second"),
				new Example3Detail("third"));

		helper.persistFlushAndClear(new Example3(id, details));
		Example3 entity = repository.load(id);

		// when
		Set<Example3Detail> newDetails = newHashSet(new Example3Detail("foo"), new Example3Detail("bar"));
		entity.editDetails(newDetails);
		repository.save(entity);
		helper.clear();

		// then
		entity = repository.load(id);
		assertThat(entity.getDetails()).isEqualTo(newDetails);
	}
}
