
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerApplicationRepository repository;


	// AbstractShowService<Employer, Application> interface -------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return this.repository.findOneByApplicationId(request.getModel().getInteger("id")).getJob().getEmployer().getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.getStatus().equals("REJECTED")) {
			request.unbind(entity, model, "reference", "moment", "lastUpdate", "status", "statement", "skills", "qualifications", "rejectReason");
		} else {
			request.unbind(entity, model, "reference", "moment", "lastUpdate", "status", "statement", "skills", "qualifications");
		}

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneByApplicationId(id);

		return result;
	}

}
