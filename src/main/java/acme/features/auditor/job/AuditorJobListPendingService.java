
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobListPendingService implements AbstractListService<Auditor, Job> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	AuditorJobRepository repository;


	// AbstractListService<Employer, Job> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Collection<Job> result;

		result = this.repository.findManyAll();
		Principal principal = request.getPrincipal();
		Collection<Job> audited = this.repository.findManyByAuditorId(principal.getActiveRoleId());
		result.removeAll(audited);

		return result;
	}

}
