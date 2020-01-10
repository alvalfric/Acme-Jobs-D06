
package acme.features.employer.duty;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.descriptor.id = (select d.descriptor.id from Duty d where d.id = 1577)")
	Job findOneJobByDutyId(int id);

	@Query("select j.descriptor.duties from Job j where j.id = ?1")
	Collection<Duty> findManyDutyByJobId(int jobId);

	@Transactional
	@Modifying
	@Query("delete from Duty d where d.id = ?1")
	void deleteDescriptorDutyById(int id);
}
