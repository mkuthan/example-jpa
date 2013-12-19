package ddd.domain;

import java.io.Serializable;

public interface Repository<E, K extends Serializable> {

	E load(K entityId);

	void save(E entity);

	void delete(E entity);

}
