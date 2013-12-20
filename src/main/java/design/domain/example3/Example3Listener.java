package design.domain.example3;

import static com.google.common.collect.Sets.newHashSet;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.google.common.collect.FluentIterable;

public class Example3Listener {

	@PostLoad
	void supplyDetails(Example3 entity) {
		Set<Example3DetailEntity> detailEntities = entity.getDetailEntities();

		entity.supplyDetails(toValueObjects(detailEntities));
	}

	@PrePersist
	@PreUpdate
	void supplyDetailEntities(Example3 entity) {
		Set<Example3Detail> details = entity.getDetails();

		entity.supplyDetailEntities(toEntities(details));
	}

	private Set<Example3Detail> toValueObjects(Set<Example3DetailEntity> detailEntities) {
		return newHashSet(FluentIterable.from(detailEntities).transform(Example3DetailEntity.toValueObject()));
	}

	private HashSet<Example3DetailEntity> toEntities(Set<Example3Detail> details) {
		return newHashSet(FluentIterable.from(details).transform(Example3DetailEntity.toEntity()));
	}

}
