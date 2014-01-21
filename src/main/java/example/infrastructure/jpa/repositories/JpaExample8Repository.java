package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Repository;

import ddd.infrastructure.jpa.GenericJpaRepository;
import example.domain.example8.Example8;
import example.domain.example8.Example8Repository;

@Repository
public class JpaExample8Repository extends GenericJpaRepository<Example8, String> implements Example8Repository {
}
