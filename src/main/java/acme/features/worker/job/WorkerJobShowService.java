
package acme.features.worker.job;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	WorkerJobRepository repository;


	// AbstractListService<Employer, Job> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		// TODO Auto-generated method stub
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
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "finalMode", "descriptor.description", "descriptor.duties");
		Descriptor descriptor = entity.getDescriptor();
		model.setAttribute("descriptor", descriptor);
		Collection<Duty> duties = descriptor.getDuties();
		model.setAttribute("duties", duties);

		model.setAttribute("canApply", this.repository.findApplicationByJobIdAndUserId(request.getPrincipal().getAccountId(), entity.getId()));

		int applicationNumber = this.repository.countApplicationsByUserAccountAndJob(request.getPrincipal().getAccountId(), Integer.parseInt(request.getModel().getAttribute("id").toString()));
		model.setAttribute("canApply", applicationNumber < 1);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		result.getDescriptor().getDuties().size();

		return result;
	}

}
