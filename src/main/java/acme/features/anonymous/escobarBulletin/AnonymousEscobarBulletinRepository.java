
package acme.features.anonymous.escobarBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.escobarBulletin.EscobarBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousEscobarBulletinRepository extends AbstractRepository {

	@Query("select e from EscobarBulletin e")
	Collection<EscobarBulletin> findMany();
}
