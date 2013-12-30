package design.domain.example3;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Function;

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

	public static Function<Example3DetailEntity, Example3Detail> toValueObject() {
		return new Function<Example3DetailEntity, Example3Detail>() {
			@Override
			public Example3Detail apply(Example3DetailEntity input) {
				return input.getDetail();
			}
		};
	}

	public static Function<Example3Detail, Example3DetailEntity> toEntity() {
		return new Function<Example3Detail, Example3DetailEntity>() {
			@Override
			public Example3DetailEntity apply(Example3Detail input) {
				Example3DetailEntity entity = new Example3DetailEntity();
				entity.setDetail(input);
				return entity;
			}
		};
	}
}
