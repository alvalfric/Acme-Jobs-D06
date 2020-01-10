
package acme.features.authenticated.announcement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("select o from Announcement o where o.id = ?1")
	Announcement findOneById(int id);

	@Query("select o from Announcement o")

	Collection<Announcement> findManyAll();

}
