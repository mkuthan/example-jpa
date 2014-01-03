package design.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import design.domain.example6.Example6;
import design.domain.example6.Example6Clob;
import design.domain.example6.Example6Repository;
import design.domain.example6.clob.ClobSerializer;

@Test
public class JpaExample6RepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private Example6Repository repository;

	@Autowired
	private ClobSerializer clobSerializer;

	public void shouldSerializeClob() {
		// given
		String id = "any id";
		Example6Clob clob = new Example6Clob("any name");

		Example6 givenEntity = new Example6(id, clob);

		// when
		when(clobSerializer.toClob(eq(clob))).thenReturn("any value");
		when(clobSerializer.fromClob(eq("any value"), eq(Example6Clob.class.getName()))).thenReturn(clob);

		repository.save(givenEntity);
		clear();

		// then
		Example6 entity = repository.load(id);

		assertThat(entity.getValueObject()).isEqualTo(clob);
	}
}
