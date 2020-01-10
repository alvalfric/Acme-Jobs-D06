
package acme.features.authenticated.requestAuditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestAuditors.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

	//	@Query("select a from RequestAuditor a where a.userAccountId = ?1")
	//	RequestAuditor findOneRequestAuditorByUserAccountId(int id);
	//
	//	@Query("select count(a) from RequestAuditor a where a.userAccountId = ?1")
	//	int countAuditorRequestByUserAccountId(int id);

	@Query("select a from RequestAuditor a where a.userAccount.id = ?1")
	RequestAuditor findOneRequestAuditorByUserAccountId(int id);

	@Query("select count(a) from RequestAuditor a where a.userAccount.id = ?1")
	int countAuditorRequestByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
