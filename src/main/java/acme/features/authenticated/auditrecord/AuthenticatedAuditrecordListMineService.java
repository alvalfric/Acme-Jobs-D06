
package acme.features.authenticated.auditrecord;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditrecordListMineService implements AbstractListService<Authenticated, Auditrecord> {

	@Autowired
	AuthenticatedAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Calendar calendar;
		Date today;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);

		calendar = new GregorianCalendar();
		today = calendar.getTime();

		result = job.isFinalMode() && job.getDeadline().after(today);

		return result;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "moment");

	}

	@Override
	public Collection<Auditrecord> findMany(final Request<Auditrecord> request) {
		assert request != null;

		Collection<Auditrecord> result;
		int jobId = request.getModel().getInteger("jobId");

		result = this.repository.findManyByJobId(jobId);
		return result;
	}

}
