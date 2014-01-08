package example.infrastructure.jpa.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.Date;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ddd.infrastructure.jpa.JpaRepositoryHelper;
import example.domain.example5.Example5;
import example.domain.example5.Example5Repository;
import example.domain.example5.audit.AuditIdentity;
import example.domain.example5.audit.Auditable;
import example.domain.example5.audit.Auditor;

@JpaRepositoryTest
@Test
public class JpaExample5RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private Example5Repository repository;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	private JpaRepositoryHelper helper;

	public void shouldUpdateAudit() {
		// given
		String id = "any id";

		final Date creationDate = new Date();
		final AuditIdentity creator = new AuditIdentity("modifier identity", "modifier details");

		final Date modificationDate = new Date();
		final AuditIdentity modifier = new AuditIdentity("modifier identity", "modifier details");

		Example5 entity = new Example5(id);

		// when
		doAnswer(new AuditorAnswer(creationDate, creator)).when(auditor).applyOn(any(Auditable.class));

		repository.save(entity);
		helper.clear();

		// then
		entity = repository.load(id);

		assertThat(entity.getAudit().getCreator()).isEqualTo(creator);
		assertThat(entity.getAudit().getModifier()).isEqualTo(creator);
		assertThat(entity.getAudit().getCreationDate()).isEqualTo(creationDate);
		assertThat(entity.getAudit().getModificationDate()).isEqualTo(creationDate);

		// when
		doAnswer(new AuditorAnswer(modificationDate, modifier)).when(auditor).applyOn(any(Auditable.class));

		repository.save(entity);
		helper.clear();

		// then
		entity = repository.load(id);

		assertThat(entity.getAudit().getCreator()).isEqualTo(creator);
		assertThat(entity.getAudit().getModifier()).isEqualTo(modifier);
		assertThat(entity.getAudit().getCreationDate()).isEqualTo(creationDate);
		assertThat(entity.getAudit().getModificationDate()).isEqualTo(modificationDate);

	}

	private class AuditorAnswer implements Answer<Void> {

		private Date modificationDate;
		private AuditIdentity modifier;

		private AuditorAnswer(Date modificationDate, AuditIdentity modifier) {
			this.modificationDate = modificationDate;
			this.modifier = modifier;
		}

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			Object[] arguments = invocation.getArguments();
			Auditable auditable = (Auditable) arguments[0];

			auditable.updateAudit(modificationDate, modifier);

			return null;
		}
	}
}
