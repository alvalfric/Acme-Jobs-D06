
package acme.features.authenticated.activechallenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedActiveChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id=?1 and TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Challenge findOneById(int id);

	@Query("select a from Challenge a where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Collection<Challenge> findManyAll();

}
