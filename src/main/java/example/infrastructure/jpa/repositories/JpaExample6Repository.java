package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example6.Example6;
import example.domain.example6.Example6Repository;

@Component
public class JpaExample6Repository extends GenericJpaRepository<Example6, String> implements Example6Repository {
}
