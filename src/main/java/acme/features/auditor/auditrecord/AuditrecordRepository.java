
package acme.features.auditor.auditrecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditrecordRepository extends AbstractRepository {

	@Query("select ar from Auditrecord ar where ar.id = ?1")
	Auditrecord findOneAuditrecordById(int id);

	@Query("select ar from Auditrecord ar where ar.auditor.id= ?1")
	Collection<Auditrecord> findManyByAuditrecordId(int auditorId);

	@Query("select ar from Auditrecord ar where ar.auditor.id = ?1 and ar.job.id = ?2 ")
	Collection<Auditrecord> findManyByJobIdAndAuditorId(int auditorId, int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

}
