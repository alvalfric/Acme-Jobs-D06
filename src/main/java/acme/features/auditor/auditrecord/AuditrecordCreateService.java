
package acme.features.auditor.auditrecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditrecordCreateService implements AbstractCreateService<Auditor, Auditrecord> {

	@Autowired
	AuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "status", "body");
		Job job = entity.getJob();
		model.setAttribute("job", job);
		Auditor auditor = entity.getAuditor();
		model.setAttribute("auditor", auditor);
		job.getApplications().size();
		job.getAuditrecords().size();
	}

	@Override
	public Auditrecord instantiate(final Request<Auditrecord> request) {
		assert request != null;

		Auditrecord result = new Auditrecord();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result.setMoment(moment);

		int userAccountId = request.getPrincipal().getAccountId();
		int jobId = request.getModel().getInteger("jobId");

		Auditor auditor = this.repository.findOneAuditorByUserAccountId(userAccountId);
		Job job = this.repository.findOneJobById(jobId);

		job.getAuditrecords().add(result);

		result.setReference("JOB-" + jobId + ":AUDITOR-" + auditor.getId());

		result.setAuditor(auditor);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {

		if (!errors.hasErrors("body")) {
			errors.state(request, !entity.getBody().isEmpty(), "body", "auditor.auditrecord.error.NotBlank");
		}
		if (!errors.hasErrors("reference")) {
			errors.state(request, !entity.getReference().isEmpty(), "reference", "auditor.auditrecord.error.NotBlank");
		}
		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "auditor.auditrecord.error.NotBlank");
		}

	}

	@Override
	public void create(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
