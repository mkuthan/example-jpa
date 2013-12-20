package design.domain.example3;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@EntityListeners(Example3Listener.class)
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

	public void setDetails(Set<Example3Detail> details) {
		this.details = details;
	}

	Set<Example3DetailEntity> getDetailEntities() {
		return detailEntities;
	}

	void supplyDetails(Set<Example3Detail> details) {
		this.details = details;
	}

	void supplyDetailEntities(HashSet<Example3DetailEntity> detailEntities) {
		this.detailEntities.clear();
		this.detailEntities.addAll(detailEntities);
	}

}
