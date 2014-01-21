package example.domain.example7;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

public class SequenceValue {

	private String prefix;

	private String separator;

	private int sequence;

	public SequenceValue(String prefix, String separator, int sequence) {
		this.prefix = requireNonNull(prefix);
		this.separator = requireNonNull(separator);

		checkState(sequence >= 0, "Sequence must be positive but was " + sequence);
		this.sequence = sequence;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSeparator() {
		return separator;
	}

	public int getSequence() {
		return sequence;
	}

	public String getValue() {
		return prefix + separator + sequence;
	}

}
