package design.domain.example5.audit;

import com.google.common.base.Optional;

public interface AuthenticatedUserProvider {

	Optional<AuthenticatedUser> authenticatedUser();

}
