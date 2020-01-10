
package acme.features.anonymous.chellikBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chellikBulletin.ChellikBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousChellikBulletinRepository extends AbstractRepository {

	@Query("select s from ChellikBulletin s")
	Collection<ChellikBulletin> findMany();
}
