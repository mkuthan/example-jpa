package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.example5.audit.Auditor;
import example.domain.example6.clob.ClobSerializer;

@Configuration
public class JpaTestConfig {

	@Bean
	public Auditor auditor() {
		return Mockito.mock(Auditor.class);
	}

	@Bean
	public ClobSerializer clobSerializer() {
		return Mockito.mock(ClobSerializer.class);
	}

}