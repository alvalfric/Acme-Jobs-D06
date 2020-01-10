
package acme.features.employer.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerApplicationRepository repository;


	// AbstractShowService<Employer, Application> interface -------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.getModel().getString("status").equals("REJECTED")) {
			request.unbind(entity, model, "reference", "moment", "lastUpdate", "status", "statement", "skills", "qualifications", "rejectReason");
		}
		if (request.getModel().getString("status").equals("ACCEPTED")) {
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

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.getModel().getString("status").equals("REJECTED")) {
			request.bind(entity, errors, "status", "rejectReason");
		}
		if (request.getModel().getString("status").equals("ACCEPTED")) {
			request.bind(entity, errors, "status");
		}
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.getModel().getString("status").equals("REJECTED") && !errors.hasErrors("rejectReason")) {
			errors.state(request, !request.getModel().getString("rejectReason").isEmpty(), "rejectReason", "employer.application.error.notBlank");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setLastUpdate(moment);

		entity.setStatus(request.getModel().getString("status"));
		if (request.getModel().getString("status").equals("REJECTED")) {
			if (!request.getModel().getString("rejectReason").isEmpty()) {
				entity.setRejectReason(request.getModel().getString("rejectReason"));
			}
		}

		this.repository.save(entity);
	}

}
