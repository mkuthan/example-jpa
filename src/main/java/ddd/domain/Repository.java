package ddd.domain;


public interface Repository<E, K> {

	E load(K entityId);

	void save(E entity);

	void delete(E entity);

}
