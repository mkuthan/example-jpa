package design.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.AbstractJpaRepository;
import design.domain.example3.Example3;
import design.domain.example3.Example3Repository;

@Component
public class JpaExample3Repository extends AbstractJpaRepository<Example3, String> implements Example3Repository {
}
