
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	AuditorJobRepository repository;


	// AbstractListService<Employer, Job> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return this.repository.findOneJobById(request.getModel().getInteger("id")).isFinalMode();
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "id", "salary", "moreInfo", "finalMode", "descriptor.description", "descriptor.duties");
		Descriptor descriptor = entity.getDescriptor();
		model.setAttribute("descriptor", descriptor);
		Collection<Duty> duties = descriptor.getDuties();
		model.setAttribute("duties", duties);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

}
