
package acme.features.authenticated.company_record;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.company_record.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCompanyRecordRepository extends AbstractRepository {

	@Query("select cr from CompanyRecord cr where cr.id = ?1")
	CompanyRecord findOneById(int id);

	@Query("select cr from CompanyRecord cr")
	Collection<CompanyRecord> findManyAll();

}
