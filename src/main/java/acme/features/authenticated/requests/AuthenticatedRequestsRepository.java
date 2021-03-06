
package acme.features.authenticated.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requests;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestsRepository extends AbstractRepository {

	@Query("select r from Requests r where r.id = ?1 and r.deadline>=current_timestamp()")
	Requests findOneById(int id);

	@Query("select r from Requests r where r.deadline>=current_timestamp()")
	Collection<Requests> findManyAll();

}
