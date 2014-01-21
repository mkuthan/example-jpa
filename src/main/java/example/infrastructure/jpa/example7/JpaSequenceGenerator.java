package example.infrastructure.jpa.example7;

import static java.util.Objects.requireNonNull;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import example.domain.example7.Sequence;
import example.domain.example7.SequenceGenerator;
import example.domain.example7.SequenceValue;

@Component
@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
public class JpaSequenceGenerator implements SequenceGenerator {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(String name, String separator, int initialSequence) {
		Sequence sequence = new Sequence(name, separator, initialSequence);
		entityManager.persist(sequence);
		entityManager.flush();
	}

	@Override
	public SequenceValue next(String name) {
		requireNonNull(name);

		TypedQuery<Sequence> query = entityManager.createQuery("SELECT s FROM Sequence s WHERE s.name = :name",
				Sequence.class);
		query.setParameter("name", name);
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);

		Sequence sequence = query.getSingleResult();
		SequenceValue next = sequence.next();
		entityManager.flush();

		return next;
	}

}
