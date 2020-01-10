
package acme.features.auditor.auditrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditrecordUpdateService implements AbstractUpdateService<Auditor, Auditrecord> {

	@Autowired
	AuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result;
		int audRecId;
		Auditrecord auditRecord;
		Principal principal;

		audRecId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOneAuditrecordById(audRecId);
		principal = request.getPrincipal();

		result = auditRecord.getAuditor().getId() == principal.getActiveRoleId();
		return result;
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
	public Auditrecord findOne(final Request<Auditrecord> request) {

		return this.repository.findOneAuditrecordById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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
	public void update(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
