
package acme.features.employer.auditrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditrecordShowService implements AbstractShowService<Employer, Auditrecord> {

	@Autowired
	EmployerAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result;
		int audRecId;
		Job job;
		Employer employer;
		Auditrecord auditRecord;
		Principal principal;

		audRecId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOneAuditrecordById(audRecId);
		job = auditRecord.getJob();
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result && auditRecord.isStatus();
	}

	@Override
	public Auditrecord findOne(final Request<Auditrecord> request) {

		assert request != null;

		Auditrecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);
		return result;

	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "moment");
		request.unbind(entity, model, "status", "body");

	}
}
