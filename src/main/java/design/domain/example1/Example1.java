package design.domain.example1;

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

	private String field;

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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
