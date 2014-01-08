package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example3.Example3;
import example.domain.example3.Example3Repository;

@Component
public class JpaExample3Repository extends GenericJpaRepository<Example3, String> implements Example3Repository {
}
