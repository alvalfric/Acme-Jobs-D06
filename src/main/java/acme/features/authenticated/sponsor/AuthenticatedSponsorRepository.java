
package acme.features.authenticated.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Sponsor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSponsorRepository extends AbstractRepository {

	@Query("select a from Sponsor a where a.userAccount.id = ?1")
	Sponsor findOneAuditorByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
