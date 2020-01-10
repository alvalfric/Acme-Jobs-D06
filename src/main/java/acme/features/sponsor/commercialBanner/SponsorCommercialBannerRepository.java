package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneById(int id);

	@Query("select b from CommercialBanner b")
	Collection<CommercialBanner> findManyAll();

	@Query("select b from CommercialBanner b where b.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorBUserAccountyId(int id);

	@Query("select o from CustomisationParameter o ")
	CustomisationParameter findOneCustomisationParameterById();

}
