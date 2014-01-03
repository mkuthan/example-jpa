package design.domain.example5.audit;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import ddd.domain.AbstractValueObject;

@Embeddable
public class Audit extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	public static final Audit NULL = new Audit();

	@Column(name = "audit_creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = AuditIdentity.IDENTITY_PROPERTY_NAME, column = @Column(name = "audit_creator_identity", nullable = false, updatable = false)),
			@AttributeOverride(name = AuditIdentity.DETAILS_PROPERTY_NAME, column = @Column(name = "audit_creator_details", nullable = false, updatable = false)) })
	private AuditIdentity creator;

	@Column(name = "audit_modification_date", nullable = false)
	private Date modificationDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = AuditIdentity.IDENTITY_PROPERTY_NAME, column = @Column(name = "audit_modifier_identity", nullable = false)),
			@AttributeOverride(name = AuditIdentity.DETAILS_PROPERTY_NAME, column = @Column(name = "audit_modifier_details", nullable = false)) })
	private AuditIdentity modifier;

	protected Audit() {
	}

	protected Audit(Date creationDate, AuditIdentity creator, Date modificationDate, AuditIdentity modifier) {
		this.creationDate = requireNonNull(creationDate);
		this.creator = requireNonNull(creator);
		this.modificationDate = requireNonNull(modificationDate);
		this.modifier = requireNonNull(modifier);
	}

	public Audit update(Date modificationDate, AuditIdentity modifier) {
		Date creationDate = (this.creationDate == null) ? modificationDate : this.creationDate;
		AuditIdentity creator = (this.creator == null) ? modifier : this.modifier;

		return new Audit(creationDate, creator, modificationDate, modifier);
	}

	public Date getCreationDate() {
		checkState(!isNull());
		return creationDate;
	}

	public AuditIdentity getCreator() {
		checkState(!isNull());
		return creator;
	}

	public Date getModificationDate() {
		checkState(!isNull());
		return modificationDate;
	}

	public AuditIdentity getModifier() {
		checkState(!isNull());
		return modifier;
	}

	private boolean isNull() {
		return equals(NULL);
	}

}