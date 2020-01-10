
package acme.features.authenticated.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id=?1 and a.deadline>=current_timestamp()")
	Challenge findOneById(int id);

	@Query("select a from Challenge a where a.deadline>=current_timestamp()")
	Collection<Challenge> findManyAll();

}
