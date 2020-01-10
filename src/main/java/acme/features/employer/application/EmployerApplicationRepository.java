
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select o from Application o where o.id = ?1")
	Application findOneByApplicationId(int id);

	@Query("select o from Application o")
	Collection<Application> findManyAll();

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 and a.status = ?2")
	Collection<Application> findManyByEmployerIdAndStatus(int employerId, String status);

}
