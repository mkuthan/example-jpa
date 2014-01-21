package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Repository;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example2.Example2;
import example.domain.example2.Example2Identifier;
import example.domain.example2.Example2Repository;

@Repository
public class JpaExample2Repository extends GenericJpaRepository<Example2, Example2Identifier> implements
		Example2Repository {
}
