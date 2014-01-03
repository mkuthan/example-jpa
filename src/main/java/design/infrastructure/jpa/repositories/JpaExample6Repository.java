package design.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.AbstractJpaRepository;
import design.domain.example6.Example6;
import design.domain.example6.Example6Repository;

@Component
public class JpaExample6Repository extends AbstractJpaRepository<Example6, String> implements Example6Repository {
}
