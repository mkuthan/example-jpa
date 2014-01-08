package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example5.Example5;
import example.domain.example5.Example5Repository;

@Component
public class JpaExample5Repository extends GenericJpaRepository<Example5, String> implements Example5Repository {
}
