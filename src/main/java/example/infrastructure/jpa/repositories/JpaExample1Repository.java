package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example1.Example1;
import example.domain.example1.Example1Repository;

@Component
public class JpaExample1Repository extends GenericJpaRepository<Example1, String> implements Example1Repository {
}
