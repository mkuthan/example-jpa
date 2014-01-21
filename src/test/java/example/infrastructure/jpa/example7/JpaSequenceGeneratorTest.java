package example.infrastructure.jpa.example7;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.domain.example7.SequenceGenerator;
import example.domain.example7.SequenceValue;
import example.infrastructure.jpa.JpaTests;

@JpaTests
@Test
public class JpaSequenceGeneratorTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@PersistenceContext
	private EntityManager entityManager;

	public void shouldGenerateNext() {
		// given
		String name = "name";
		String separator = "-";
		int initialSequence = 0;

		sequenceGenerator.initialize(name, separator, initialSequence);
		entityManager.clear();

		// when
		SequenceValue next = sequenceGenerator.next(name);

		// then
		assertThat(next.getPrefix()).isEqualTo(name);
		assertThat(next.getSeparator()).isEqualTo(separator);
		assertThat(next.getSequence()).isEqualTo(initialSequence + 1);
	}
}
