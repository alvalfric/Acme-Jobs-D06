
package acme.features.anonymous.rosadoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.rosadoBulletin.RosadoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRosadoBulletinRepository extends AbstractRepository {

	@Query("select b from RosadoBulletin b")
	Collection<RosadoBulletin> findMany();
}
