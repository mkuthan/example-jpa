package design.domain.example4;

import static java.util.Objects.requireNonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Example4 {

	@Id
	private String id;

	@Version
	private Integer version;

	@Transient
	@Autowired
	private Example4Service service;

	Example4() {
	}

	public Example4(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void callService() {
		service.call();
	}

}
