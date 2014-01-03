package design.domain.example5.audit;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embeddable;

@Embeddable
public class AuthenticatedUser {

	private String username;

	private String fullname;

	AuthenticatedUser() {
	}

	public AuthenticatedUser(String username, String fullname) {
		this.username = requireNonNull(username);
		this.fullname = requireNonNull(fullname);
	}

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

}
