package example.domain.example7;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sequence {

	@Id
	@GeneratedValue
	private String id;

	@Column(nullable = false, unique = true, updatable = false)
	private String name;

	@Column(nullable = false, updatable = false)
	private String separator;

	private int sequence;

	Sequence() {
	}

	public Sequence(String name, String separator, int sequence) {
		this.name = requireNonNull(name);
		this.separator = requireNonNull(separator);

		checkState(sequence >= 0, "Initial sequence must be positive but was " + sequence);
		this.sequence = sequence;
	}

	public SequenceValue next() {
		sequence++;
		return new SequenceValue(name, separator, sequence);
	}

}
