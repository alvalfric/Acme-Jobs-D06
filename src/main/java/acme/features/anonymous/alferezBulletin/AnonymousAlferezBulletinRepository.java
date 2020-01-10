
package acme.features.anonymous.alferezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.alferezBulletin.AlferezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousAlferezBulletinRepository extends AbstractRepository {

	@Query("select s from AlferezBulletin s")
	Collection<AlferezBulletin> findMany();
}
