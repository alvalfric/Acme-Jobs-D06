package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select b from NonCommercialBanner b where b.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select b from NonCommercialBanner b")
	Collection<NonCommercialBanner> findManyAll();

	@Query("select b from NonCommercialBanner b where b.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorBUserAccountyId(int id);

	@Query("select o from CustomisationParameter o ")
	CustomisationParameter findOneCustomisationParameterById();

}
