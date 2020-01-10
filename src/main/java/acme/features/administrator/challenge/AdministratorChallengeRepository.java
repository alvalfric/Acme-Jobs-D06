
package acme.features.administrator.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id=?1 and a.deadline>=current_timestamp()")
	Challenge findOneChallengeById(int id);

	@Query("select o from Challenge o where o.title=?1 and o.deadline>=current_timestamp()")
	Challenge findOneByTitle(String title);

	@Query("select i from Challenge i where i.deadline>=current_timestamp()")
	Collection<Challenge> findManyAll();

}
