
package acme.features.anonymous.investorRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecords.InvestorRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousInvestorRecordRepository extends AbstractRepository {

	@Query("select r from InvestorRecord r")
	Collection<InvestorRecord> findManyAll();

	@Query("select r from InvestorRecord r where r.id = ?1")
	InvestorRecord findOneById(int id);

	@Query("select r from InvestorRecord r where r.stars = 5")
	Collection<InvestorRecord> findManyTop();

}
