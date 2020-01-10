
package acme.features.authenticated.auditrecord;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditrecordShowService implements AbstractShowService<Authenticated, Auditrecord> {

	@Autowired
	AuthenticatedAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result;
		int audRecId;
		Job job;
		Auditrecord auditRecord;
		Calendar calendar;
		Date today;

		audRecId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOneAuditrecordById(audRecId);
		job = auditRecord.getJob();

		calendar = new GregorianCalendar();
		today = calendar.getTime();

		result = job.isFinalMode() && job.getDeadline().after(today);

		return result;
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

		request.unbind(entity, model, "reference", "title", "moment", "status", "body");

	}
}
