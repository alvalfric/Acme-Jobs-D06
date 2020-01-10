
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select o from Application o where o.id = ?1")
	Application findOneByApplicationId(int id);

	@Query("select count(a) from Application a where a.worker.userAccount.id = ?1 and a.job.id = ?2")
	int countApplicationsByUserAccountAndJob(int apId, int jobId);

	@Query("select o from Application o")
	Collection<Application> findManyAll();

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerId);

}
