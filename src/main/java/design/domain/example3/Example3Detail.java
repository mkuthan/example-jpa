package design.domain.example3;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embeddable;

import ddd.domain.AbstractValueObject;

@Embeddable
public class Example3Detail extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	private String name;

	Example3Detail() {
	}

	public Example3Detail(String name) {
		this.name = requireNonNull(name);
	}

	public String getName() {
		return name;
	}

}
