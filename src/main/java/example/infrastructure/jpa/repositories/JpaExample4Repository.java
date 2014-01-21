package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Repository;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example4.Example4;
import example.domain.example4.Example4Repository;

@Repository
public class JpaExample4Repository extends GenericJpaRepository<Example4, String> implements Example4Repository {
}
