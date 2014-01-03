package design.domain.example6.clob;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

public class ClobHolder<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T value;

	ClobHolder() {
	}

	public static <T> ClobHolder<T> of(T value) {
		ClobHolder<T> jsonHolder = new ClobHolder<T>();
		jsonHolder.value = requireNonNull(value);
		return jsonHolder;
	}

	public static <T> ClobHolder<T> absent() {
		return new ClobHolder<T>();
	}

	public T getValue() {
		return value;
	}

	public boolean hasValue() {
		return value != null;
	}

}