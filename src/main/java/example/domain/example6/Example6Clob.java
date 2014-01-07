package example.domain.example6;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embeddable;

import ddd.domain.AbstractValueObject;

@Embeddable
public class Example6Clob extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	private String name;

	Example6Clob() {
	}

	public Example6Clob(String name) {
		this.name = requireNonNull(name);
	}

	public String getName() {
		return name;
	}

}
