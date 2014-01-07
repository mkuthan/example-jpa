package example.infrastructure.jpa.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.example5.audit.Auditable;
import example.domain.example5.audit.Auditor;

@Component
public class JpaAuditListener {

	private static Auditor auditor;

	@Autowired
	public void setDateTimeProvider(Auditor auditor) {
		JpaAuditListener.auditor = auditor;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;
			auditor.applyOn(auditable);
		}
	}
}