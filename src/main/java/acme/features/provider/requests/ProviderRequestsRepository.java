
package acme.features.provider.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requests;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestsRepository extends AbstractRepository {

	@Query("select r from Requests r where r.id = ?1 and r.deadline>=current_timestamp()")
	Requests findOneById(int id);

	@Query("select r from Requests r where r.ticker = ?1 and r.deadline>=current_timestamp()")
	Requests findOneByTicker(String ticker);

	@Query("select r from Requests r where r.deadline>=current_timestamp()")
	Collection<Requests> findManyAll();

}
