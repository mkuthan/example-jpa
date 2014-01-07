package example.domain.example6;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.Columns;

import example.domain.example6.clob.ClobHolder;

@Entity
public class Example6 {

	@Id
	private String id;

	@Version
	private Integer version;

	@Columns(columns = { @Column(name = "value_object_json_class"), @Column(name = "value_object_json_value") })
	private ClobHolder<Example6Clob> clobHolder = ClobHolder.absent();

	Example6() {
	}

	public Example6(String id, Example6Clob valueObject) {
		this.id = requireNonNull(id);
		this.clobHolder = ClobHolder.of(valueObject);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public Example6Clob getValueObject() {
		return clobHolder.getValue();
	}

}
