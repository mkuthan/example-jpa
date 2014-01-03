package design.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.AbstractJpaRepository;
import design.domain.example1.Example1;
import design.domain.example1.Example1Repository;

@Component
public class JpaExample1Repository extends AbstractJpaRepository<Example1, String> implements Example1Repository {
}
