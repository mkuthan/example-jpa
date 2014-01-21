package example.domain.example7;

public interface SequenceGenerator {

	void initialize(String name, String separator, int initialSequence);

	SequenceValue next(String name);
}
