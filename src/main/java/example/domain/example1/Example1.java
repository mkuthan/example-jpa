package example.domain.example1;

import static java.util.Objects.requireNonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Example1 {

	@Id
	private String id;

	@Version
	private Integer version;

	Example1() {
	}

	public Example1(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

}
