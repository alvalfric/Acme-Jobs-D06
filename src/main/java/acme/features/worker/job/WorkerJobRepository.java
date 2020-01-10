
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1 and j.deadline>=current_timestamp() and j.finalMode=true")
	Job findOneJobById(int id);

	@Query("select a from Application a where a.worker.id = (select w from Worker w where w.userAccount.id = ?1) and a.job.id = ?2")
	Application findApplicationByJobIdAndUserId(int userId, int jobId);

	@Query("select j from Job j where j.deadline>=current_timestamp() and j.finalMode=true")
	Collection<Job> findManyAll();

	@Query("select count(a) from Application a where a.worker.userAccount.id = ?1 and a.job.id = ?2")
	int countApplicationsByUserAccountAndJob(int apId, int jobId);
}
