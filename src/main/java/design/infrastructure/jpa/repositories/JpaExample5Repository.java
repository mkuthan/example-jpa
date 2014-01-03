package design.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import ddd.infrastructure.jpa.AbstractJpaRepository;
import design.domain.example5.Example5;
import design.domain.example5.Example5Repository;

@Component
public class JpaExample5Repository extends AbstractJpaRepository<Example5, String> implements Example5Repository {
}
