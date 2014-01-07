package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.AbstractJpaRepository;
import example.domain.example4.Example4;
import example.domain.example4.Example4Repository;

@Component
public class JpaExample4Repository extends AbstractJpaRepository<Example4, String> implements Example4Repository {
}
