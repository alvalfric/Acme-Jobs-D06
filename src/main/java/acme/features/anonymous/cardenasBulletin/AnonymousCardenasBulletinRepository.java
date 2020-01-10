
package acme.features.anonymous.cardenasBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cardenasBulletin.CardenasBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCardenasBulletinRepository extends AbstractRepository {

	@Query("select s from CardenasBulletin s")
	Collection<CardenasBulletin> findMany();
}
