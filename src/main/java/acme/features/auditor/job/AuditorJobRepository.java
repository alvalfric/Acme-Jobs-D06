
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {
	/*
	 * @Query("select j from Job j where j.id = ?1 and j.deadline>=current_timestamp() and j.finalMode=true")
	 * Job findOneJobById(int id);
	 *
	 * @Query("select j from Job j where j.deadline>=current_timestamp() and j.finalMode=true")
	 * Collection<Job> findManyAll();
	 */

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.finalMode = 1")
	Collection<Job> findManyAll();

	@Query("select a.job from Auditrecord a where a.auditor.id= ?1 group by a.job")
	Collection<Job> findManyByAuditorId(int auditorId);

}
