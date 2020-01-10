
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.authenticated.job.AuthenticatedJobRepository;
import acme.features.authenticated.worker.AuthenticatedWorkerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	WorkerApplicationRepository		repository;

	@Autowired
	AuthenticatedWorkerRepository	workerRespository;

	@Autowired
	AuthenticatedJobRepository		jobRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int applicationNumber;
		boolean result;

		applicationNumber = this.repository.countApplicationsByUserAccountAndJob(request.getPrincipal().getAccountId(), Integer.parseInt(request.getModel().getAttribute("jobId").toString()));
		result = applicationNumber < 1;
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "lastUpdate");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "statement", "skills", "qualifications", "moment", "lastUpdate");
		Job job = entity.getJob();
		model.setAttribute("job", job);
		Worker worker = entity.getWorker();
		model.setAttribute("worker", worker);
		model.setAttribute("jobId", entity.getJob().getId());

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result = new Application();
		Principal principal = request.getPrincipal();
		Integer id = principal.getAccountId();
		Worker worker = this.workerRespository.findOneWorkerBUserAccountyId(id);

		Integer jobId = request.getModel().getInteger("jobId");
		Job job = this.jobRepository.findOneJobById(jobId);
		result.setWorker(worker);
		result.setJob(job);
		String jobref = job.getReference();
		String name = principal.getUsername();
		String name4 = name.substring(0, 4);
		String reference = (jobref + ":" + name4).toUpperCase();
		result.setStatus("PENDING");
		result.setReference(reference);
		result.setSkills(worker.getSkillsRecord());
		result.setQualifications(worker.getQualificationsRecord());
		result.setMoment(new Date(System.currentTimeMillis() - 1));
		job.getApplications().add(result);
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
			errors.state(request, entity.getReference().length() > 3 && entity.getReference().length() < 27, "qualifications", "authenticated.application.error.Reference");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		entity.setLastUpdate(moment);
		this.repository.save(entity);

	}

}
