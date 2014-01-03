package design.domain.example3;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Entity
public class Example3DetailEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	private Example3Detail detail;

	public Example3Detail getDetail() {
		return detail;
	}

	public void setDetail(Example3Detail detail) {
		this.detail = detail;
	}

	public static Set<Example3Detail> toValueObjects(Set<Example3DetailEntity> detailEntities) {
		return newHashSet(FluentIterable.from(detailEntities).transform(toValueObject()));
	}

	public static HashSet<Example3DetailEntity> toEntities(Set<Example3Detail> details) {
		return newHashSet(FluentIterable.from(details).transform(toEntity()));
	}

	private static Function<Example3DetailEntity, Example3Detail> toValueObject() {
		return new Function<Example3DetailEntity, Example3Detail>() {
			@Override
			public Example3Detail apply(Example3DetailEntity input) {
				requireNonNull(input);
				return input.getDetail();
			}
		};
	}

	private static Function<Example3Detail, Example3DetailEntity> toEntity() {
		return new Function<Example3Detail, Example3DetailEntity>() {
			@Override
			public Example3DetailEntity apply(Example3Detail input) {
				requireNonNull(input);
				Example3DetailEntity entity = new Example3DetailEntity();
				entity.setDetail(input);
				return entity;
			}
		};
	}
}
