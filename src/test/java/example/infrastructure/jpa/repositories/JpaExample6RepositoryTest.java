package example.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example6.Example6;
import example.domain.example6.Example6Clob;
import example.domain.example6.Example6Repository;
import example.domain.example6.clob.ClobSerializer;

@JpaRepositoryTest
@Test
public class JpaExample6RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example6Repository repository;

	@Autowired
	private ClobSerializer clobSerializer;
	
	@Autowired
	private JpaRepositoryHelper helper;

	public void shouldSerializeClob() {
		// given
		String id = "any id";
		Example6Clob clob = new Example6Clob("any name");

		Example6 givenEntity = new Example6(id, clob);

		// when
		when(clobSerializer.toClob(eq(clob))).thenReturn("any value");
		when(clobSerializer.fromClob(eq("any value"), eq(Example6Clob.class.getName()))).thenReturn(clob);

		repository.save(givenEntity);
		helper.clear();

		// then
		Example6 entity = repository.load(id);

		assertThat(entity.getValueObject()).isEqualTo(clob);
	}
}
