package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Repository;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example3.Example3;
import example.domain.example3.Example3Repository;

@Repository
public class JpaExample3Repository extends GenericJpaRepository<Example3, String> implements Example3Repository {
}
