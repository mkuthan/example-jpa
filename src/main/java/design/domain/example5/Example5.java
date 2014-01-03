package design.domain.example5;

import static java.util.Objects.requireNonNull;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import design.domain.example5.audit.Audit;
import design.domain.example5.audit.AuditIdentity;
import design.domain.example5.audit.Auditable;

@Entity
public class Example5 implements Auditable {

	@Id
	private String id;

	@Version
	private Integer version;

	@Embedded
	private Audit audit = Audit.NULL;

	Example5() {
	}

	public Example5(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void updateAudit(Date modificationDate, AuditIdentity modifier) {
		this.audit = audit.update(modificationDate, modifier);
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

}
