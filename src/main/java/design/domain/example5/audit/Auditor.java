package design.domain.example5.audit;

import static java.util.Objects.requireNonNull;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

@Component
public class Auditor {

	private AuthenticatedUserProvider authenticatedUserProvider;

	private DateProvider dateProvider;

	@Autowired
	public Auditor(AuthenticatedUserProvider authenticatedUserProvider, DateProvider dateProvider) {
		this.authenticatedUserProvider = authenticatedUserProvider;
		this.dateProvider = dateProvider;
	}

	public void applyOn(Auditable auditable) {
		requireNonNull(auditable);

		Date modificationDate = getModificationDate();
		AuditIdentity modifer = getModifier();

		auditable.updateAudit(modificationDate, modifer);
	}

	private Date getModificationDate() {
		Date now = dateProvider.currentDate();
		return now;
	}

	private AuditIdentity getModifier() {
		Optional<AuthenticatedUser> authenticatedUser = authenticatedUserProvider.authenticatedUser();
		AuditIdentity modifer = AuditIdentity.NOT_AVAILABLE;
		if (authenticatedUser.isPresent()) {
			modifer = new AuditIdentity(authenticatedUser.get().getUsername(), authenticatedUser.get().getFullname());
		}
		return modifer;
	}

}