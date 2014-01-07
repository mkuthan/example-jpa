package example.domain.example2;

import static java.util.Objects.requireNonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Example2 {

	@Id
	private Example2Identifier id;

	@Version
	private Integer version;

	Example2() {
	}

	public Example2(Example2Identifier id) {
		this.id = requireNonNull(id);
	}

	public Example2Identifier getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

}
