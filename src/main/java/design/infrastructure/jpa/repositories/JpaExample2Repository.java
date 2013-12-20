package design.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.AbstractJpaRepository;
import design.domain.example2.Example2;
import design.domain.example2.Example2Identifier;
import design.domain.example2.Example2Repository;

@Component
public class JpaExample2Repository extends AbstractJpaRepository<Example2, Example2Identifier> implements
		Example2Repository {
}
