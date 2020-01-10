
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

public class WorkerApplicationUpdateService implements AbstractUpdateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "statement", "skills", "qualifications");

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
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("reference")) {
			errors.state(request, !entity.getReference().isEmpty(), "reference", "authenticated.application.error.NotBlank");
		}
		if (!errors.hasErrors("status")) {
			errors.state(request, entity.getStatus() != null, "status", "authenticated.application.error.NotNull");
		}
		if (!errors.hasErrors("moment")) {
			errors.state(request, entity.getMoment() != null, "moment", "authenticated.application.error.NotNull");
		}
		if (!errors.hasErrors("statements")) {
			errors.state(request, !entity.getStatement().isEmpty(), "statements", "authenticated.application.error.NotBlank");
		}
		if (!errors.hasErrors("skills")) {
			errors.state(request, !entity.getSkills().isEmpty(), "skills", "authenticated.application.error.NotBlank");
		}
		if (!errors.hasErrors("qualifications")) {
			errors.state(request, !entity.getQualifications().isEmpty(), "qualifications", "authenticated.application.error.NotBlank");
		}
		if (!errors.hasErrors("reference") && entity.getReference() != null) {
			errors.state(request, entity.getReference().length() > 5 && entity.getReference().length() < 15, "qualifications", "authenticated.application.error.Reference");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
