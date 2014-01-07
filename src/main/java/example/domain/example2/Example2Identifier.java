package example.domain.example2;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embeddable;

import ddd.domain.AbstractValueObject;

@Embeddable
public class Example2Identifier extends AbstractValueObject {


	private static final long serialVersionUID = 1L;

	private String id;

	public Example2Identifier(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
