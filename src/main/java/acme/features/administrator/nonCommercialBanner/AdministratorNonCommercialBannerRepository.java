
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select b from NonCommercialBanner b where b.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select b from NonCommercialBanner b")
	Collection<NonCommercialBanner> findManyAll();

}
