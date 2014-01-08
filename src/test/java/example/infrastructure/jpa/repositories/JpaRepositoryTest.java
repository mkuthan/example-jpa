package example.infrastructure.jpa.repositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;

import example.infrastructure.jpa.JpaConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = JpaConfig.class)
public @interface JpaRepositoryTest {
}