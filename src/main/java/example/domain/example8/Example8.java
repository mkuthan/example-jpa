package example.domain.example8;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Example8 {

	@Id
	private String id;

	@Version
	private Integer version;

	@Column(nullable = false, unique = true)
	private String name;

	Example8() {
	}

	public Example8(String id, String name) {
		this.id = requireNonNull(id);
		this.name = requireNonNull(name);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

}
