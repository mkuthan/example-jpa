package example.domain.example5.audit;

import java.util.Date;

public interface Auditable {

	void updateAudit(Date modificationDate, AuditIdentity modifier);

	Audit getAudit();

}