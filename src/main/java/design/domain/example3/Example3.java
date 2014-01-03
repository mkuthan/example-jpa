package design.domain.example3;

import static design.domain.example3.Example3DetailEntity.toEntities;
import static design.domain.example3.Example3DetailEntity.toValueObjects;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class Example3 {

	@Id
	private String id;

	@Version
	private Integer version;

	@Transient
	private Set<Example3Detail> details = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(nullable = false, updatable = false)
	private Set<Example3DetailEntity> detailEntities = new HashSet<>();

	Example3() {
	}

	public Example3(String id, Set<Example3Detail> details) {
		this.id = requireNonNull(id);
		this.details = requireNonNull(details);

		supplyDetailsEntities();
	}

	@PostLoad
	void supplyDetails() {
		this.details = toValueObjects(detailEntities);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public Set<Example3Detail> getDetails() {
		return details;
	}

	public void editDetails(Set<Example3Detail> details) {
		if (!this.details.equals(details)) {
			this.details = requireNonNull(details);
			supplyDetailsEntities();
		}
	}

	private void supplyDetailsEntities() {
		this.detailEntities.clear();
		this.detailEntities.addAll(toEntities(this.details));
	}

}
