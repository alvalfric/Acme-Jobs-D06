
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("jobId", request.getModel().getAttribute("jobId"));

		request.unbind(entity, model, "title", "description", "percentage");
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result;
		Job job;
		job = this.repository.findOneJobById(Integer.parseInt(request.getModel().getAttribute("jobId").toString()));
		result = new Duty();
		result.setDescriptor(job.getDescriptor());

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		double dutyPercentage = 0;

		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "title empty");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !entity.getDescription().isEmpty(), "description", "description empty");
		}
		if (!errors.hasErrors("percentage")) {
			errors.state(request, entity.getPercentage() != null, "percentage", "percentage empty");
		}
		if (entity.getPercentage() > 100) {
			errors.state(request, entity.getPercentage() != null, "percentage", "no mas 100 empty");
		}

		for (Duty duty : entity.getDescriptor().getDuties()) {
			dutyPercentage += duty.getPercentage();
			System.out.println(duty.getPercentage());
		}

		dutyPercentage += entity.getPercentage();

		if (dutyPercentage > 100.0) {
			System.out.println("mayor");
			errors.state(request, !(dutyPercentage > 100.0), "percentage", "duties mayor de 100");
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Duty> request, final Response<Duty> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
