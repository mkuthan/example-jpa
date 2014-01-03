package design.domain.example5.audit;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embeddable;

import ddd.domain.AbstractValueObject;

@Embeddable
public class AuditIdentity extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	public static final AuditIdentity NOT_AVAILABLE = new AuditIdentity("N/A", "N/A");

	public static final String IDENTITY_PROPERTY_NAME = "identity";

	public static final String DETAILS_PROPERTY_NAME = "details";

	private String identity;

	private String details;

	protected AuditIdentity() {
	}

	public AuditIdentity(String identity, String details) {
		this.identity = requireNonNull(identity);
		this.details = requireNonNull(details);
	}

	public String getIdentity() {
		return identity;
	}

	public String getDetails() {
		return details;
	}

}